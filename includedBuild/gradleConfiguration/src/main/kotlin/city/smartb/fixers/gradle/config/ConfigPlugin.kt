package city.smartb.fixers.gradle.config

import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		val mainConfig = target.createExtension()
		target.allprojects {
			extensions.fixersIfExists {
				extensions.configure(FixersExtension::class.java) {
					this.bundle = mainConfig.bundle
					this.sonar = mainConfig.sonar
					this.publication = mainConfig.publication
					this.repository = mainConfig.repository
				}
			}
			target.afterEvaluate {
				extensions.fixers?.let { config ->
					println("***************************************************")
					println(config.bundle.name)
					println(config.bundle.description)
					println(config.bundle.version)
					println(config.repository.id)
					println(config.repository.name)
					println("***************************************************")
				}
			}
		}

	}

	private fun Project.createExtension() = extensions.findByType(FixersExtension::class.java) ?: extensions.create(
		FixersExtension.NAME,
		FixersExtension::class.java,
		this@createExtension
	)
}
