plugins {
    kotlin("multiplatform")
    id("com.moowork.node")
}

dependencies {
    commonMainApi("city.smartb.ssm:ssm-dsl:${Versions.ssm}")
}
