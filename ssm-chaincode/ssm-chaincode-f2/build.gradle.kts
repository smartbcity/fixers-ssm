plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
	kotlin("plugin.spring")
	id("org.jetbrains.kotlin.kapt")
}

dependencies {
	api(project(":ssm-chaincode:ssm-chaincode-dsl"))
	api("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")

	api(project(":ssm-sdk:ssm-sdk-sign"))
	implementation(project(":ssm-chaincode:ssm-chaincode-client"))

	testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}

}
