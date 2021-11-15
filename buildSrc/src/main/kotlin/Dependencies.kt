import org.gradle.api.artifacts.Dependency

object PluginVersions {
	const val kotlin = "1.6.0-RC2"
	const val fixers = "experimental-SNAPSHOT"

	const val springBoot = "2.5.3"

	const val npmPublish = "1.0.4"
}

object Versions {
	const val springBoot = PluginVersions.springBoot
	const val springFramework = "5.3.4"

	const val ktor = "1.6.3"

	const val cloudant = "0.0.28"
	const val bouncycastleVersion = "1.61"
	const val jacksonKotlin = "2.13.0"
	const val slf4j = "1.7.30"

	const val f2 = "experimental-SNAPSHOT"

	const val cucumber = "6.11.0"
	const val junit = "5.7.0"
	const val junitPlateform = "1.8.1"
	const val assertj = "3.21.0"
}

object Dependencies {
	object Jvm {
		fun slf4j(scope: Scope) = scope.add(
			"org.slf4j:slf4j-api:${Versions.slf4j}"
		)

		fun ktorClient(scope: Scope) = scope.add(
			"io.ktor:ktor-client-core:${Versions.ktor}",
			"io.ktor:ktor-client-cio:${Versions.ktor}",
			"io.ktor:ktor-client-jackson:${Versions.ktor}"
		)

		fun junit(scope: Scope) = scope.add(
			"org.junit.jupiter:junit-jupiter:${Versions.junit}",
			"org.junit.jupiter:junit-jupiter-api:${Versions.junit}",
			"org.junit.platform:junit-platform-suite:${Versions.junitPlateform}",
			"org.assertj:assertj-core:${Versions.assertj}"
		)

		fun cucumber(scope: Scope) = scope.add(
			"io.cucumber:cucumber-java8:${Versions.cucumber}",
			"io.cucumber:cucumber-junit-platform-engine:${Versions.cucumber}",
			"org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}"
		).also(::junit)

	}
}

typealias Scope = (dependencyNotation: Any) -> Dependency?

fun Scope.add(vararg deps: String): Scope {
	deps.forEach { this(it) }
	return this
}
