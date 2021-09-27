plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {
	api(project(":ssm-chaincode:ssm-chaincode-dsl"))
	api(project(":ssm-chaincode:ssm-chaincode-client"))
	api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.coroutines}")


	api(project(":ssm-sdk:ssm-sdk-json"))
}
