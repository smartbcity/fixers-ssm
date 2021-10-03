plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
	kotlin("plugin.spring")
	kotlin("kapt")
}

dependencies {
	api(project(":ssm-spring:ssm-tx-create-ssm-spring-boot-starter"))
	api(project(":ssm-spring:ssm-tx-session-perform-action-spring-boot-starter"))
	api(project(":ssm-spring:ssm-tx-session-start-spring-boot-starter"))

	testImplementation(project(":ssm-test:ssm-test-cucumber-spring-autoconfigure"))
}