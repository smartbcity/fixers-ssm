object PluginVersions {
	const val kotlin = "1.5.32"
	const val fixers = "0.1.1"

	const val springBoot = "2.3.4.RELEASE"

	const val npmPublish = "1.0.4"
}

object Versions {
	const val springBoot = PluginVersions.springBoot
	const val springFramework = "5.2.9.RELEASE"

	const val cloudant = "0.0.24"

	const val bouncycastleVersion = "1.61"
	const val okhttpVersion = "3.14.0"
	const val retrofitVersion = "2.5.0"
	const val jacksonKotlin = "2.10.2"

	const val junit = "5.7.0"
	const val assertj = "3.15.0"

	const val slf4j = "1.7.30"


	const val coroutines = "1.4.2"
	const val kserialization = "1.0.0"
	const val ktor = "1.4.1"
	const val rsocket = "0.13.1"

	const val f2 = "0.1.0"
//	const val d2 = "0.1.1-SNAPSHOT"
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
