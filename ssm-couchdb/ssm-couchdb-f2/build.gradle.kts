plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {
	api(project(":ssm-couchdb:ssm-couchdb-dsl"))
	implementation(project(":ssm-couchdb:ssm-couchdb-sdk"))

	testImplementation(project(":ssm-bdd:ssm-bdd-couchdb"))
	testImplementation("org.junit.platform:junit-platform-suite:${Versions.junitPlateform}")
}
