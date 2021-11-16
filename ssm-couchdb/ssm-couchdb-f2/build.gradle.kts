plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {
	api(project(":ssm-couchdb:ssm-couchdb-dsl"))
	implementation(project(":ssm-couchdb:ssm-couchdb-sdk"))

	testImplementation("org.junit.platform:junit-platform-suite:${Versions.junitPlateform}")

	testImplementation(project(":ssm-couchdb:ssm-couchdb-bdd"))
}
