
plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	kotlin("plugin.spring")
}

dependencies {
	api("org.springframework.boot:spring-boot-starter-webflux:${city.smartb.gradle.dependencies.FixersVersions.Spring.boot}")
	implementation("city.smartb.ssm:ssm-data-spring-boot-starter:experimental-SNAPSHOT")
	implementation("city.smartb.ssm:ssm-tx-spring-boot-starter:experimental-SNAPSHOT")

}
