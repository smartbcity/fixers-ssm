object PluginVersions {
	const val kotlin = "1.5.21"
	const val dokka = "1.5.0"
	const val springBoot = "2.5.3"

	const val npmPublish = "1.0.4"
	const val sonarQube = "3.0"
}

object Versions {
	const val guava = "30.1-jre"
	const val springBoot = PluginVersions.springBoot
	const val springFramework = "5.3.4"
	const val springData = "2.4.5"

	const val cloudant = "0.0.24"

	const val bouncycastleVersion = "1.61"
	const val okhttpVersion = "3.14.0"
	const val retrofitVersion = "2.5.0"
	const val jacksonKotlin = "2.10.2"

	const val junit = "5.7.0"
	const val assertj = "3.15.0"

	const val slf4j = "1.7.30"


	const val coroutines = "1.5.1"
	const val kserialization = "1.1.0"
	const val ktor = "1.6.1"
	const val rsocket = "0.13.1"

	const val d2 = "0.1.0-SNAPSHOT"
	const val f2 = "experimental-SNAPSHOT"
}

object Dependencies {
	object jvm {
		val coroutines = arrayOf(
			"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.coroutines}"
		)
		val junit = arrayOf(
			"org.junit.jupiter:junit-jupiter:${Versions.junit}",
			"org.junit.jupiter:junit-jupiter-api:${Versions.junit}",
			"org.assertj:assertj-core:${Versions.assertj}"
		)
	}

	object common {
		val coroutines = arrayOf(
			"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
		)
		val kserialization = arrayOf(
			"org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kserialization}",
			"org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kserialization}"
		)
	}
}