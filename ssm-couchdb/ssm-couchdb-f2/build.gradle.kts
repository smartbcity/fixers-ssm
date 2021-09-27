plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {
	api(project(":ssm-couchdb:ssm-couchdb-dsl"))
	implementation(project(":ssm-couchdb:ssm-couchdb-client"))

	testImplementation(project(":ssm-test:ssm-test-cucumber"))
	testImplementation("org.junit.platform:junit-platform-suite:${Versions.junitPlateform}")
}
