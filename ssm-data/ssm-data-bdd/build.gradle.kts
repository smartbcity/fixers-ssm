plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
}

dependencies {
	api(project(":ssm-data:ssm-data-f2"))
	api(project(":ssm-bdd:ssm-bdd-features"))
	api(project(":ssm-tx:ssm-tx-bdd"))
}
