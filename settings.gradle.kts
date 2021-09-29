pluginManagement {
	repositories {
		gradlePluginPortal()
		maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
	}
}

rootProject.name = "ssm"

include(
	"ssm-chaincode:ssm-chaincode-client",
	"ssm-chaincode:ssm-chaincode-dsl",
	"ssm-chaincode:ssm-chaincode-f2",
	"ssm-chaincode:ssm-chaincode-f2:f2-commons",
	"ssm-chaincode:ssm-chaincode-f2:f2-query",
	"ssm-chaincode:ssm-chaincode-f2:f2-create-ssm",
	"ssm-chaincode:ssm-chaincode-f2:f2-session-perform-action",
	"ssm-chaincode:ssm-chaincode-f2:f2-session-start",
	"ssm-chaincode:ssm-chaincode-f2-client"
)

include(
	"ssm-couchdb:ssm-couchdb-client",
	"ssm-couchdb:ssm-couchdb-dsl",
	"ssm-couchdb:ssm-couchdb-f2"
)

include(
	"ssm-sdk:ssm-sdk-sign",
	"ssm-sdk:ssm-sdk-json"
)

include(
	"ssm-spring:ssm-chaincode-spring-boot-starter",
	"ssm-spring:ssm-couchdb-spring-boot-starter",
	"ssm-spring:ssm-data-spring-boot-starter",
	"ssm-spring:ssm-create-ssm-spring-boot-starter",
	"ssm-spring:ssm-session-perform-action-spring-boot-starter",
	"ssm-spring:ssm-session-start-spring-boot-starter",
)

include(
	"ssm-test:ssm-test-cucumber",
	"ssm-test:ssm-test-cucumber-spring-autoconfigure"
)
include(
	"ssm-data:ssm-data-client",
	"ssm-data:ssm-data-dsl",
	"ssm-data:ssm-data-f2"
)
