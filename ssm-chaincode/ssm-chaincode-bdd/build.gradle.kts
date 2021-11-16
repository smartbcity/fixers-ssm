plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
}

dependencies {
	api(project(":ssm-chaincode:ssm-chaincode-f2"))
	api(project(":ssm-sdk:ssm-sdk-bdd"))
	Dependencies.Jvm.cucumber(::api)
}
