plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("lt.petuska.npm.publish")
}

dependencies {
    commonMainApi(project(":ssm-tx:ssm-tx-dsl"))

    commonMainApi("city.smartb.f2:f2-client-ktor:${Versions.f2}")
    commonMainApi("city.smartb.f2:f2-dsl-function:${Versions.f2}")
    commonMainApi("city.smartb.f2:f2-dsl-cqrs:${Versions.f2}")
}

apply(from = rootProject.file("gradle/publishing-mpp.gradle"))