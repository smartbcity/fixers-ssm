plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.jetbrains.kotlin.kapt")
}

dependencies {
    api(project(":ssm-dsl"))
    api("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")

    implementation(project(":ssm-sdk-client"))
    implementation(project(":ssm-sdk-client-spring"))

    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

}

apply(from = rootProject.file("gradle/publishing.gradle"))