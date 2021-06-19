plugins {
    kotlin("multiplatform")
}

dependencies {
    commonMainApi(project(":ssm-chaincode:ssm-chaincode-dsl"))
}
