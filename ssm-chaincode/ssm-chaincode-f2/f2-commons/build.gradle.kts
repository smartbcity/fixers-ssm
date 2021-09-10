plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
	kotlin("plugin.spring")
}

dependencies {
	api(project(":ssm-chaincode:ssm-chaincode-dsl"))
	api(project(":ssm-chaincode:ssm-chaincode-client"))

	api("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")
	api(project(":ssm-sdk:ssm-sdk-json"))
}
