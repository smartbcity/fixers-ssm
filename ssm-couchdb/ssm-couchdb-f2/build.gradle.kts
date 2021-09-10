plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
	kotlin("plugin.spring")
}

dependencies {

	implementation(project(":ssm-couchdb:ssm-couchdb-autoconfiguration"))
	api(project(":ssm-couchdb:ssm-couchdb-dsl"))

	api("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")
	testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}
