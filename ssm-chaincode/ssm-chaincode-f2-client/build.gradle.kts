import city.smartb.gradle.dependencies.FixersVersions

plugins {
	id("city.smartb.fixers.gradle.kotlin.mpp")
	kotlin("plugin.serialization")
	id("lt.petuska.npm.publish")
}

dependencies {
	commonMainApi(project(":ssm-chaincode:ssm-chaincode-dsl"))

	commonMainApi("city.smartb.f2:f2-client-ktor:${FixersVersions.f2}")
	commonMainApi("city.smartb.f2:f2-dsl-cqrs:${FixersVersions.f2}")
	commonMainApi("city.smartb.f2:f2-dsl-function:${FixersVersions.f2}")
}
