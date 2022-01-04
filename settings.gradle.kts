pluginManagement {
	repositories {
		gradlePluginPortal()
		maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
	}
}

rootProject.name = "ssm"

include(
	"ssm-tx:ssm-tx-autoconfiguration",
//	"ssm-tx:ssm-tx-client",
	"ssm-tx:ssm-tx-dsl",
	"ssm-tx:ssm-tx-f2"
)

include(
	"ssm-couchdb:ssm-couchdb-autoconfiguration",
	"ssm-couchdb:ssm-couchdb-client",
	"ssm-couchdb:ssm-couchdb-dsl",
	"ssm-couchdb:ssm-couchdb-f2"
)

include(
	"ssm-chaincode:ssm-chaincode-client",
	"ssm-chaincode:ssm-chaincode-dsl",
	"ssm-chaincode:ssm-chaincode-f2",
//	"ssm-chaincode:ssm-chaincode-f2-client"
)

include(
	"ssm-sdk:ssm-sdk-sign",
	"ssm-sdk:ssm-sdk-json"
)

include(
	"ssm-chaincode:ssm-chaincode-f2:f2-commons",
	"ssm-chaincode:ssm-chaincode-f2:f2-query",
	"ssm-chaincode:ssm-chaincode-f2:f2-create-ssm",
	"ssm-chaincode:ssm-chaincode-f2:f2-session-perform-action",
	"ssm-chaincode:ssm-chaincode-f2:f2-session-start"
)
