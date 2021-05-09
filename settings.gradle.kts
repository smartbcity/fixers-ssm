pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
	}
}

rootProject.name = "ssm"

include(
	"ssm-couchdb:ssm-couchdb-autoconfiguration",
	"ssm-couchdb:ssm-couchdb-client",
	"ssm-couchdb:ssm-couchdb-f2"
)

include(
        "ssm-dsl",
        "ssm-f2",
        "ssm-f2-client"
)

include(
	"ssm-sdk-client",
	"ssm-sdk-client-sign",
	"ssm-sdk:ssm-sdk-json"
)


include(
        "ssm-f2:ssm-f2-commons",
        "ssm-f2:ssm-f2-query",
        "ssm-f2:ssm-f2-create-ssm",
        "ssm-f2:ssm-f2-session-perform-action",
        "ssm-f2:ssm-f2-session-start"
)
