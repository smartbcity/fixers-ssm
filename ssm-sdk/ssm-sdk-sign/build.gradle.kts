plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.bouncycastle:bcprov-jdk15on:${ Versions.bouncycastleVersion}")
    implementation("com.google.guava:guava:${Versions.guava}")

}

apply(from = rootProject.file("gradle/publishing.gradle"))