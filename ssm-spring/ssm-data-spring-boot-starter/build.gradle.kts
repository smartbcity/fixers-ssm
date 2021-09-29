plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
	kotlin("plugin.spring")
	kotlin("kapt")
}

dependencies {
	api(project(":ssm-data:ssm-data-f2"))

	api("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")
	kapt("org.springframework.boot:spring-boot-configuration-processor:${Versions.springBoot}")

	testImplementation(project(":ssm-test:ssm-test-cucumber-spring-autoconfigure"))
}