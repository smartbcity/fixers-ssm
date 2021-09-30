plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {
	api(project(":ssm-sdk:ssm-sdk-sign"))
	implementation(project(":ssm-chaincode:ssm-chaincode-dsl"))
	implementation(project(":ssm-sdk:ssm-sdk-json"))
	implementation("org.slf4j:slf4j-api:${Versions.slf4j}")

	implementation("org.bouncycastle:bcprov-jdk15on:${Versions.bouncycastleVersion}")

	implementation("io.ktor:ktor-client-core:${Versions.ktor}")
	implementation("io.ktor:ktor-client-cio:${Versions.ktor}")
	implementation("io.ktor:ktor-client-jackson:${Versions.ktor}")
}
