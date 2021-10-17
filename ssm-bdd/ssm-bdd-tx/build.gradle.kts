plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
}

dependencies {
	implementation(project(":ssm-bdd:ssm-bdd-sdk"))
	Dependencies.Jvm.cucumber(::api)
}
