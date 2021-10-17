plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
}

dependencies {
//	api("ssm-bdd:ssm-bdd-cucumber")
	Dependencies.Jvm.cucumber(::api)
}
