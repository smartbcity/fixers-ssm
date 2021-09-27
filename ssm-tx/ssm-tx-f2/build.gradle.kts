plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {
	api(project(":ssm-tx:ssm-tx-dsl"))

	api(project(":ssm-couchdb:ssm-couchdb-f2"))
	api(project(":ssm-chaincode:ssm-chaincode-f2:f2-query"))
}
