plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

// To make it available as direct dependency
group = "city.smartb.gradle.dependencies"
version = "SNAPSHOT"

repositories {
    jcenter()
}

gradlePlugin {
    plugins.register("dependencies") {
        id = "dependencies"
        implementationClass = "city.smartb.gradle.dependencies.DependenciesPlugin"
    }
}
