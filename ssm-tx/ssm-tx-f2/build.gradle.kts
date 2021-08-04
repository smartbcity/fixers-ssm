plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    api(project(":ssm-tx:ssm-tx-dsl"))
    api(project(":ssm-tx:ssm-tx-autoconfiguration"))

    api(project(":ssm-couchdb:ssm-couchdb-f2"))
    api(project(":ssm-chaincode:ssm-chaincode-f2:f2-query"))

    implementation("city.smartb.f2:f2-spring-boot-starter-function-http:${Versions.f2}")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

apply(from = rootProject.file("gradle/publishing.gradle"))