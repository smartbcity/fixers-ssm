pluginManagement {
	repositories {
		gradlePluginPortal()
		jcenter()
	}
}

rootProject.name = "ssm"

enableFeaturePreview("GRADLE_METADATA")

include(
	"ssm-dsl",
	"ssm-f2",
	"ssm-f2-client"
)
include(
	"ssm-sdk-client",
	"ssm-sdk-client-spring"
)
