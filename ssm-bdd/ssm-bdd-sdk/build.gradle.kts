plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
}

dependencies {
	api(project(":ssm-sdk:ssm-sdk-core"))
	api(project(":ssm-chaincode:ssm-chaincode-dsl"))

	Dependencies.Jvm.cucumber(::api)
}
