plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {

	api(project(":ssm-sdk:ssm-sdk-sign"))
	implementation("org.bouncycastle:bcprov-jdk15on:${Versions.bouncycastleVersion}")

}
