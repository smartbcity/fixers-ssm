plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {
	api(project(":ssm-chaincode:ssm-chaincode-dsl"))
	api(project(":ssm-sdk:ssm-sdk-sign"))
	api(project(":ssm-sdk:ssm-sdk-json"))

	Dependencies.Jvm.slf4j(::implementation)
	Dependencies.Jvm.ktorClient(::implementation)

	testImplementation(project(":ssm-sdk:ssm-sdk-bdd"))
}
