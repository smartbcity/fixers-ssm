plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("dependencies")
}

repositories {
    google()
    jcenter()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${city.smartb.gradle.dependencies.PluginVersions.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:${city.smartb.gradle.dependencies.PluginVersions.kotlin}")

    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${city.smartb.gradle.dependencies.PluginVersions.detekt}")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:${city.smartb.gradle.dependencies.PluginVersions.dokka}")
    implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${city.smartb.gradle.dependencies.PluginVersions.sonar}")

    implementation("city.smartb.gradle.dependencies:dependencies:SNAPSHOT")
}

gradlePlugin {
    plugins.register("city.smartb.fixers.gradle.kotlin.mpp") {
        id = "city.smartb.fixers.gradle.kotlin.mpp"
        implementationClass = "city.smartb.fixers.gradle.kotlin.MppPlugin"
    }
    plugins.register("city.smartb.fixers.gradle.kotlin.jvm") {
        id = "city.smartb.fixers.gradle.kotlin.jvm"
        implementationClass = "city.smartb.fixers.gradle.kotlin.JvmPlugin"
    }
    plugins.register("city.smartb.fixers.gradle.publish") {
        id = "city.smartb.fixers.gradle.publish"
        implementationClass = "city.smartb.fixers.gradle.publish.PublishPlugin"
    }
    plugins.register("city.smartb.fixers.gradle.d2") {
        id = "city.smartb.fixers.gradle.d2"
        implementationClass = "city.smartb.fixers.gradle.d2.D2Plugin"
    }
//    plugins.register("detekt-configuration") {
//        id = "detekt-configuration"
//        implementationClass = "city.smartb.gradle.detekt.DetektPlugin"
//    }
    plugins.register("city.smartb.fixers.gradle.config") {
        id = "city.smartb.fixers.gradle.config"
        implementationClass = "city.smartb.fixers.gradle.config.ConfigPlugin"
    }
    plugins.register("city.smartb.fixers.gradle.sonar") {
        id = "city.smartb.fixers.gradle.sonar"
        implementationClass = "city.smartb.fixers.gradle.sonar.SonarPlugin"
    }
}
