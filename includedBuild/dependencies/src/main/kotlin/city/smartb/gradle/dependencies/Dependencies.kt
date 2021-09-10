package city.smartb.gradle.dependencies

object PluginVersions {
	const val kotlin = "1.5.30"
	const val detekt = "1.18.1"
	const val dokka = "1.4.32"
	const val sonar = "3.3"
	const val springBoot = "2.5.3"

	const val npmPublish = "1.0.4"
	const val sonarQube = "3.0"
}

object Versions {

	const val springBoot = PluginVersions.springBoot
	const val springFramework = "5.3.4"

	const val junit = "5.7.0"
	const val assertj = "3.15.0"

	const val coroutines = "1.5.1"
	const val kserialization = "1.1.0"

	const val f2 = "experimental-SNAPSHOT"
	const val d2 = "0.1.1-SNAPSHOT"
}

object Dependencies {
	object jvm {
		val coroutines = arrayOf(
			"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.coroutines}"
		)
		val test = arrayOf(
			"org.junit.jupiter:junit-jupiter:${Versions.junit}",
			"org.junit.jupiter:junit-jupiter-api:${Versions.junit}",
			"org.assertj:assertj-core:${Versions.assertj}"
		)
	}

	object common {
		val coroutines = arrayOf(
			"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
		)
		val test =  arrayOf(
			"org.jetbrains.kotlin:kotlin-test-common:${PluginVersions.kotlin}",
			"org.jetbrains.kotlin:kotlin-test-annotations-common:${PluginVersions.kotlin}"
		)
		val kserialization = arrayOf(
			"org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kserialization}",
			"org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kserialization}"
		)
	}
}

object D2 {
	const val dokkaStorybook = "dokkaStorybook"
	const  val dokkaStorybookPartial = "${dokkaStorybook}Partial"
}