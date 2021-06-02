plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    api(project(":ssm-dsl"))
    api("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")
    api(project(":ssm-sdk-client"))
    api(project(":ssm-sdk:ssm-sdk-json"))
}

apply(from = rootProject.file("gradle/publishing.gradle"))