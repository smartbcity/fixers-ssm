package city.smartb.fixers.gradle.d2

import city.smartb.gradle.dependencies.D2
import city.smartb.gradle.dependencies.Versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.register

class D2Plugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("org.jetbrains.dokka")

        target.subprojects {
            tasks {
                register<org.jetbrains.dokka.gradle.DokkaTask>(D2.dokkaStorybookPartial) {
                    dependencies {
                        plugins("city.smartb.d2:dokka-storybook-plugin:${Versions.d2}")
                    }
                }
            }
        }

        target.tasks {
            register<org.jetbrains.dokka.gradle.DokkaCollectorTask>(D2.dokkaStorybook) {
                addChildTask(D2.dokkaStorybookPartial)
                addSubprojectChildTasks(D2.dokkaStorybookPartial)
                outputDirectory.set(target.file("storybook/stories/d2"))
            }
        }
    }
}
