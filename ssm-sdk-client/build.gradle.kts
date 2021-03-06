plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":ssm-dsl"))
    implementation("org.slf4j:slf4j-api:${Versions.slf4j}")



    implementation("org.bouncycastle:bcprov-jdk15on:${ Versions.bouncycastleVersion}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${ Versions.jacksonKotlin}")

    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}")
    implementation("com.squareup.retrofit2:retrofit:${ Versions.retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-jackson:${Versions.retrofitVersion}")
    implementation("com.google.guava:guava:${Versions.guava}")

}

apply(from = rootProject.file("gradle/publishing.gradle"))