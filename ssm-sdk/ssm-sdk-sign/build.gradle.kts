plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	id("city.smartb.fixers.gradle.publish")
}

dependencies {
	implementation("org.bouncycastle:bcprov-jdk15on:${Versions.bouncycastleVersion}")

}
