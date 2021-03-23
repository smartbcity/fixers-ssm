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
	"ssm-sdk-client"
)

include(
	"ssm-f2:ssm-f2-query",
	"ssm-f2:ssm-f2-create-ssm",
	"ssm-f2:ssm-f2-session-perform-action",
	"ssm-f2:ssm-f2-session-start"
)
