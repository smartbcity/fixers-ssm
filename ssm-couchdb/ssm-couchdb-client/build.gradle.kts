plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    api(project(":ssm-dsl"))
    implementation(project(":ssm-sdk:ssm-sdk-json"))

    api("com.ibm.cloud:cloudant:${Versions.cloudant}")

}

apply(from = rootProject.file("gradle/publishing.gradle"))