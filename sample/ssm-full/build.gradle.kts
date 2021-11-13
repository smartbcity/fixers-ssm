plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	kotlin("plugin.spring")
}

dependencies {
	implementation("city.smartb.f2:f2-spring-boot-starter-function-http:${Versions.f2}")

	implementation(project(":ssm-spring:ssm-data-spring-boot-starter"))
	implementation(project(":ssm-spring:ssm-tx-spring-boot-starter"))

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
}
