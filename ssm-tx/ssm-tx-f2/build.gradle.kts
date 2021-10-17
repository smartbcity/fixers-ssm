plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {
	api(project(":ssm-tx:ssm-tx-dsl"))

	implementation(project(":ssm-chaincode:ssm-chaincode-f2:f2-commons"))
	implementation(project(":ssm-sdk:ssm-sdk-core"))
}
