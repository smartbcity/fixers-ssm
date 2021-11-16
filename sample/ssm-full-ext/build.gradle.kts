plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	kotlin("plugin.spring")
}

dependencies {
//	implementation("city.smartb.f2:f2-spring-boot-starter-function-http:${Versions.f2}")

	api("org.springframework.boot:spring-boot-starter-webflux:${Versions.springBoot}")
	implementation("city.smartb.ssm:ssm-data-spring-boot-starter:experimental-SNAPSHOT")
	implementation("city.smartb.ssm:ssm-tx-spring-boot-starter:experimental-SNAPSHOT")

}
