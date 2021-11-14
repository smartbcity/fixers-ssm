plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {

	api("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonKotlin}")

}
