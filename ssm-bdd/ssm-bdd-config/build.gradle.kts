import city.smartb.gradle.dependencies.FixersDependencies
import city.smartb.gradle.dependencies.FixersVersions

plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
}

dependencies {
	api(project(":ssm-sdk:ssm-sdk-core"))

	api("org.springframework.boot:spring-boot-starter-test:${FixersVersions.Spring.boot}")
	FixersDependencies.Jvm.Test.cucumber(::api)
}
