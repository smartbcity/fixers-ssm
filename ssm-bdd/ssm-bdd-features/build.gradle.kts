plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
}

dependencies {
	api(project(":ssm-sdk:ssm-sdk-core"))
	api(project(":ssm-bdd:ssm-bdd-config"))

	city.smartb.gradle.dependencies.FixersDependencies.Jvm.Test.junit(::api)
}
