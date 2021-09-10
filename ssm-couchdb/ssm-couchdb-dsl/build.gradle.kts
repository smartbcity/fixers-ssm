plugins {
	id("city.smartb.fixers.gradle.kotlin.mpp")
	id("city.smartb.fixers.gradle.publish")
	id("lt.petuska.npm.publish")
}

dependencies {
	commonMainApi(project(":ssm-chaincode:ssm-chaincode-dsl"))
}
