plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	kotlin("plugin.spring")
}

dependencies {
	api(project(":ssm-bdd:ssm-bdd-config"))
	Dependencies.Jvm.cucumber(::api)
}
