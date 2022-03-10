import city.smartb.gradle.dependencies.FixersPluginVersions
import city.smartb.gradle.dependencies.FixersVersions
import city.smartb.gradle.dependencies.Scope
import city.smartb.gradle.dependencies.add

object PluginVersions {
	const val fixers = "experimental-SNAPSHOT"
	const val kotlin = FixersPluginVersions.kotlin
	const val npmPublish = FixersPluginVersions.npmPublish
}

object Versions {
	const val springFramework = FixersVersions.Spring.framework
	const val slf4j = FixersVersions.Logging.slf4j
	const val cloudant = "0.0.34"
	const val bouncycastleVersion = "1.61"
	const val jacksonKotlin = "2.13.2"
	const val f2 = PluginVersions.fixers
}

object Dependencies {
	fun slf4j(scope: Scope) = scope.add(
		"org.slf4j:slf4j-api:${Versions.slf4j}",
	)
}