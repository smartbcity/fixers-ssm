import city.smartb.gradle.dependencies.FixersDependencies

plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
}

dependencies {
	api(project(":ssm-chaincode:ssm-chaincode-f2"))
	api(project(":ssm-sdk:ssm-sdk-bdd"))
	FixersDependencies.Jvm.Test.cucumber(::api)
}
