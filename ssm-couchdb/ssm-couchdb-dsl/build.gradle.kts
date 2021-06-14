plugins {
    kotlin("multiplatform")
    id("lt.petuska.npm.publish")
}

dependencies {
    commonMainApi(project(":ssm-chaincode:ssm-chaincode-dsl"))
}

apply(from = rootProject.file("gradle/publishing-mpp.gradle"))
