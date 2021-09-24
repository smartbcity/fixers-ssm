plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
	kotlin("plugin.spring")
	kotlin("kapt")
}

dependencies {
	api(project(":ssm-couchdb:ssm-couchdb-f2"))

	kapt("org.springframework.boot:spring-boot-configuration-processor:${Versions.springBoot}")

	testImplementation(project(":ssm-test:ssm-test-cucumber"))
	api("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}
