plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    api(project(":ssm-tx:ssm-tx-dsl"))

    api(project(":ssm-couchdb:ssm-couchdb-f2"))
    api(project(":ssm-chaincode:ssm-chaincode-f2:f2-query"))

    implementation("city.smartb.f2:f2-spring-boot-starter-function-http:${Versions.f2}")
}
