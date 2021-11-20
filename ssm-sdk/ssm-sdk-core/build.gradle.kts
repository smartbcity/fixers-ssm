import city.smartb.gradle.dependencies.FixersDependencies

plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {
	api(project(":ssm-chaincode:ssm-chaincode-dsl"))
	api(project(":ssm-sdk:ssm-sdk-dsl"))
	api(project(":ssm-sdk:ssm-sdk-json"))
	api(project(":ssm-sdk:ssm-sdk-sign"))
	api(project(":ssm-sdk:ssm-sdk-sign-rsa-key"))

	FixersDependencies.Jvm.Kotlin.ktorClient(::implementation)

	testImplementation(project(":ssm-sdk:ssm-sdk-bdd"))
}
