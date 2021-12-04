import city.smartb.gradle.dependencies.FixersVersions

plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
	kotlin("plugin.spring")
	kotlin("kapt")
}

dependencies {
	api(project(":ssm-tx:ssm-tx-f2"))
	api(project(":ssm-spring:ssm-tx-spring-boot-starter:ssm-tx-config-spring-boot-starter"))

	api("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")

	kapt("org.springframework.boot:spring-boot-configuration-processor:${FixersVersions.Spring.boot}")

	testImplementation(project(":ssm-bdd:ssm-bdd-spring-autoconfigure"))
}
