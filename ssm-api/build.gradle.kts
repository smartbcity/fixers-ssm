plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.jpa")
    kotlin("plugin.spring")
}

dependencies {
//    api(project(":api:ssm:smm-domain"))

    implementation("city.smartb.f2:f2-spring-boot-starter-function-http:${Versions.f2}")
}
