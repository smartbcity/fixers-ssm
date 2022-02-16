import city.smartb.gradle.dependencies.FixersPluginVersions
import city.smartb.gradle.dependencies.Scope
import city.smartb.gradle.dependencies.add

object PluginVersions {
	const val fixers = "experimental-SNAPSHOT"
	const val kotlin = FixersPluginVersions.kotlin
	const val npmPublish = FixersPluginVersions.npmPublish
}

object Versions {
	const val springFramework = "5.3.14"
	const val cloudant = "0.0.34"
	const val bouncycastleVersion = "1.61"
	const val jacksonKotlin = "2.13.0"
	const val f2 = "experimental-SNAPSHOT"
	const val slf4j = "1.7.35"
}

object Dependencies {
	fun slf4j(scope: Scope) = scope.add(
		"org.slf4j:slf4j-api:${Versions.slf4j}",
	)
}