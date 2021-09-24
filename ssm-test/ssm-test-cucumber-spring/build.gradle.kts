plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	kotlin("plugin.spring")
}

dependencies {
	api("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")

	api(project(":ssm-test:ssm-test-cucumber"))
	api("io.cucumber:cucumber-spring:${Versions.cucumber}")
	api("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}