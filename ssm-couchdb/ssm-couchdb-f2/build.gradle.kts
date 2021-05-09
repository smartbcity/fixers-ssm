plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {

    implementation(project(":ssm-couchdb:ssm-couchdb-autoconfiguration"))
    api("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")
    api(project(":ssm-dsl"))

    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

}

apply(from = rootProject.file("gradle/publishing.gradle"))