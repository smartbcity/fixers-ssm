pluginManagement {
	repositories {
		gradlePluginPortal()
		maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
	}
}

rootProject.name = "ssm"

include(
	"ssm-chaincode:ssm-chaincode-dsl",
	"ssm-chaincode:ssm-chaincode-f2",
	"ssm-chaincode:ssm-chaincode-f2:f2-commons",
	"ssm-chaincode:ssm-chaincode-f2:f2-query",
	"ssm-chaincode:ssm-chaincode-f2-client"
)

include(
	"ssm-couchdb:ssm-couchdb-sdk",
	"ssm-couchdb:ssm-couchdb-dsl",
	"ssm-couchdb:ssm-couchdb-f2"
)

include(
	"ssm-sdk:ssm-sdk-core",
	"ssm-sdk:ssm-sdk-sign",
	"ssm-sdk:ssm-sdk-json"
)

include(
	"ssm-spring:ssm-chaincode-spring-boot-starter",
	"ssm-spring:ssm-couchdb-spring-boot-starter",
	"ssm-spring:ssm-data-spring-boot-starter",
	"ssm-spring:ssm-tx-spring-boot-starter",
	"ssm-spring:ssm-tx-spring-boot-starter:ssm-tx-create-ssm-spring-boot-starter",
	"ssm-spring:ssm-tx-spring-boot-starter:ssm-tx-session-perform-action-spring-boot-starter",
	"ssm-spring:ssm-tx-spring-boot-starter:ssm-tx-session-start-spring-boot-starter",
)

include(
	"ssm-bdd:ssm-bdd-chaincode",
	"ssm-bdd:ssm-bdd-couchdb",
	"ssm-bdd:ssm-bdd-config",
	"ssm-bdd:ssm-bdd-sdk",
	"ssm-bdd:ssm-bdd-spring-autoconfigure",
	"ssm-bdd:ssm-bdd-tx"
)

include(
	"ssm-data:ssm-data-client",
	"ssm-data:ssm-data-dsl",
	"ssm-data:ssm-data-f2",
	"ssm-data:ssm-data-sync"
)

include(
	"ssm-tx:ssm-tx-dsl",
	"ssm-tx:ssm-tx-f2",
)
