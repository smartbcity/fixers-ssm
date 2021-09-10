package city.smartb.fixers.gradle.publish

import city.smartb.fixers.gradle.config.FixersExtension
import city.smartb.fixers.gradle.config.ConfigPlugin
import city.smartb.fixers.gradle.config.fixers
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.publish.maven.tasks.PublishToMavenLocal
import org.gradle.api.publish.maven.tasks.PublishToMavenRepository
import org.gradle.api.publish.plugins.PublishingPlugin
import org.gradle.internal.impldep.org.bouncycastle.asn1.x500.style.RFC4519Style.name
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.plugins.signing.SigningExtension
import org.gradle.plugins.signing.SigningPlugin
import org.jetbrains.kotlin.builtins.StandardNames.FqNames.target
import java.lang.System.getenv

class PublishPlugin : Plugin<Project> {

	override fun apply(target: Project) {
		target.plugins.apply(ConfigPlugin::class.java)
		target.plugins.apply(MavenPublishPlugin::class.java)
		target.afterEvaluate {
			extensions.fixers?.let { fixersConfig ->
				createFilteredPublishTasks()
				setupPublishing(fixersConfig)
				setupSign()
				createEmptySourcesJar()
			}
		}
	}

	private fun Project.setupPublishing(fixersConfig: FixersExtension) {
		val publishing = project.extensions.getByType(PublishingExtension::class.java)
		val publication = fixersConfig.publication
		val repository = fixersConfig.repository

		publishing.repositories {
			maven {
				name = repository.name
				url = repository.url
				credentials {
					username = repository.username
					password = repository.password
				}
			}
		}
		publishing.publications.all {
			println("1''''''''")
			println(name)
			println("1''''''''")
		}
		extensions.findByType(JavaPluginExtension::class.java)?.let {
			publishing.publications {
				create<MavenPublication>("") {
					project.components.forEach {
						println("////////////")
						println(it.name)
						println("//////////////")

					}
					from(project.components["kotlin"])
					publication?.let { pom(publication.configure) }
				}
			}
		}
		publishing.publications.all {
			println("2''''''''")
			println(name)
			println("2''''''''")

		}
	}

	private fun Project.setupSign() {
		val inMemoryKey = getenv("signingKey") ?: project.findProperty("signingKey")?.toString()
		val password = getenv("signingPassword") ?: project.findProperty("signingPassword")?.toString()
		if (inMemoryKey == null) {
			project.logger.warn("No signing config provided, skip signing")
			return
		}
		project.plugins.apply(SigningPlugin::class.java)
		project.extensions.getByType(SigningExtension::class.java).apply {
			isRequired = true
			useInMemoryPgpKeys(inMemoryKey, password)
			sign(
				project.extensions.getByType(PublishingExtension::class.java).publications
			)
		}
	}

	private fun Project.createFilteredPublishTasks() {
		// Create umbrella task with enabled publish tasks only.
		// We can't use regular publishToMavenLocal/publishAll
		// because even if task is disabled all its dependencies will be still executed.
		project.tasks.register(TASK_FILTERED_PUBLISH_TO_MAVEN_LOCAL) {
			group = PublishingPlugin.PUBLISH_TASK_GROUP
			dependsOn(
				project
					.tasks
					.withType(PublishToMavenLocal::class.java)
					.matching { it.enabled }
			)
		}
		project.tasks.register(TASK_FILTERED_PUBLISH_TO_SONATYPE) {
			group = PublishingPlugin.PUBLISH_TASK_GROUP
			dependsOn(
				project
					.tasks
					.withType(PublishToMavenRepository::class.java)
					.matching {
						it.enabled && it.repository.name == "sonatype"
					}
			)
		}
	}

	private fun Project.createEmptySourcesJar() {
		val task = project.tasks.register("javadocJar", Jar::class.java) {
			archiveClassifier.set("javadoc")

		}
		val sourcesJarTask = project.tasks.register("sourceJar", Jar::class.java) {
			archiveClassifier.set("sources")
		}

		extensions
			.getByType(PublishingExtension::class.java)
			.publications
			.withType(MavenPublication::class.java)
			.configureEach {
				artifact(task.get())
				artifact(sourcesJarTask.get())
			}
	}

	companion object {
		const val TASK_FILTERED_PUBLISH_TO_MAVEN_LOCAL = "publishAllFilteredToMavenLocal"
		const val TASK_FILTERED_PUBLISH_TO_SONATYPE = "publishAllFilteredToSonatype"
	}
}
