plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
	kotlin("plugin.spring")
	kotlin("kapt")
}

dependencies {
	api(project(":ssm-spring:ssm-tx-spring-boot-starter:ssm-tx-create-ssm-spring-boot-starter"))
	api(project(":ssm-spring:ssm-tx-spring-boot-starter:ssm-tx-init-ssm-spring-boot-starter"))
	api(project(":ssm-spring:ssm-tx-spring-boot-starter:ssm-tx-session-perform-action-spring-boot-starter"))
	api(project(":ssm-spring:ssm-tx-spring-boot-starter:ssm-tx-session-start-spring-boot-starter"))

	testImplementation(project(":ssm-bdd:ssm-bdd-spring-autoconfigure"))
}
