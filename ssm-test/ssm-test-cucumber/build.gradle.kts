plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
}

dependencies {
	api("io.cucumber:cucumber-java8:${Versions.cucumber}")
	api("io.cucumber:cucumber-junit-platform-engine:${Versions.cucumber}")
	Dependencies.Jvm.junit.forEach { api(it) }
}
