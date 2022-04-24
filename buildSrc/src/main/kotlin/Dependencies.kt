import city.smartb.gradle.dependencies.FixersPluginVersions
import city.smartb.gradle.dependencies.FixersVersions
import city.smartb.gradle.dependencies.FixersDependencies
import city.smartb.gradle.dependencies.Scope
import city.smartb.gradle.dependencies.add

object PluginVersions {
	val fixers = FixersPluginVersions.fixers
	const val d2 = "0.3.1"
	const val kotlin = FixersPluginVersions.kotlin
	const val npmPublish = FixersPluginVersions.npmPublish
}

object Versions {
	const val springFramework = FixersVersions.Spring.framework
	const val slf4j = FixersVersions.Logging.slf4j
	const val jacksonKotlin = FixersVersions.Json.jacksonKotlin
	val f2 = PluginVersions.fixers
	val ktor = "2.0.0"


	const val cloudant = "0.0.34"
	const val bouncycastleVersion = "1.61"
}

object Dependencies {
	fun slf4j(scope: Scope) = FixersDependencies.Jvm.Logging.slf4j(scope)
	// TODO Migrate to f2-client
	fun ktor(scope: Scope) = scope.add(
		"io.ktor:ktor-client-core:${Versions.ktor}",
		"io.ktor:ktor-client-content-negotiation:${Versions.ktor}",
		"io.ktor:ktor-client-cio:${Versions.ktor}",
		"io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}",
		"io.ktor:ktor-serialization-jackson:${Versions.ktor}"
	)
	fun jackson(scope: Scope) = scope.add(
		"com.fasterxml.jackson.module:jackson-module-kotlin:${FixersVersions.Json.jacksonKotlin}"
	)
}
