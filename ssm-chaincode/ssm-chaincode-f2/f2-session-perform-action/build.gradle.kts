plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    api(project(":ssm-chaincode:ssm-chaincode-dsl"))
    api("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")

    implementation(project(":ssm-chaincode:ssm-chaincode-f2:f2-commons"))
    implementation(project(":ssm-chaincode:ssm-chaincode-client"))
    api(project(":ssm-sdk:ssm-sdk-sign"))

    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

}

apply(from = rootProject.file("gradle/publishing.gradle"))