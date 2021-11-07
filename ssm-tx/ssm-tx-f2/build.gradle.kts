plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {
	api(project(":ssm-tx:ssm-tx-dsl"))

	api(project(":ssm-sdk:ssm-sdk-sign"))
	implementation(project(":ssm-sdk:ssm-sdk-core"))
}
