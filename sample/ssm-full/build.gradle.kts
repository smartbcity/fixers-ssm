plugins {
	id("org.springframework.boot")
	id("city.smartb.fixers.gradle.kotlin.jvm")
	kotlin("plugin.spring")
	id("org.graalvm.buildtools.native")
}

dependencies {
	implementation("city.smartb.f2:f2-spring-boot-starter-function-http:${Versions.f2}")

	implementation(project(":ssm-spring:ssm-data-spring-boot-starter"))
	implementation(project(":ssm-spring:ssm-tx-spring-boot-starter"))

}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootBuildImage> {
	imageName.set("smartbcity/ssm-sample-ssm-full:${this.project.version}")
}