plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.bouncycastle:bcprov-jdk15on:${ Versions.bouncycastleVersion}")

}

apply(from = rootProject.file("gradle/publishing.gradle"))