plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("kapt")
}

dependencies {
	api(project(":ssm-tx:ssm-tx-dsl"))
	compileOnly(project(":ssm-couchdb:ssm-couchdb-autoconfiguration"))

	implementation("org.springframework.boot:spring-boot-autoconfigure:${Versions.springBoot}")

	testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}

	kapt("org.springframework.boot:spring-boot-configuration-processor:${Versions.springBoot}")
}

apply(from = rootProject.file("gradle/publishing.gradle"))