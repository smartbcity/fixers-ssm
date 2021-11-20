import city.smartb.gradle.dependencies.FixersVersions

plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	kotlin("plugin.spring")
}

dependencies {
	implementation("city.smartb.f2:f2-spring-boot-starter-function-http:${FixersVersions.f2}")

	implementation(project(":ssm-spring:ssm-data-spring-boot-starter"))
	implementation(project(":ssm-spring:ssm-tx-spring-boot-starter"))

}
