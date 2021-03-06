plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation( project(":ssm-sdk-client"))

    implementation("org.springframework.boot:spring-boot-autoconfigure:${Versions.springBoot}")

    kapt("org.springframework.boot:spring-boot-configuration-processor:${Versions.springBoot}")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}")
}

apply(from = rootProject.file("gradle/publishing.gradle"))