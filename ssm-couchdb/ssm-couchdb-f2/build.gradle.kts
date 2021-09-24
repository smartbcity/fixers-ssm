plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
	kotlin("plugin.spring")
}

dependencies {
	api(project(":ssm-couchdb:ssm-couchdb-dsl"))
	implementation(project(":ssm-couchdb:ssm-couchdb-client"))

	api("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")

	testImplementation(project(":ssm-test:ssm-test-cucumber"))
	testImplementation("org.junit.platform:junit-platform-suite:${Versions.junitPlateform}")
}
