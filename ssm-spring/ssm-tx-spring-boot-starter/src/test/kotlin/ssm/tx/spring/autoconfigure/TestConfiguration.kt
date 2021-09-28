package ssm.tx.spring.autoconfigure

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.tx.dsl.config.SsmTxConfig

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "ssm")
class CucumberTests

object TestConfiguration {
	private val localDockerCompose = SsmTxConfig(
		couchdb = SsmCouchdbConfig(
			url = "http://localhost:5000",
			username = "couchdb",
			password = "couchdb",
			serviceName = "ssm-couchdb-test"
		),
		chaincode = SsmChaincodeConfig(
			url = "http://localhost:9090"
		)
	)

	val localDockerComposeParams = mapOf(
		"ssm.tx.chaincode.url" to localDockerCompose.chaincode.url,
		"ssm.tx.couchdb.url" to localDockerCompose.couchdb.url,
		"ssm.tx.couchdb.username" to localDockerCompose.couchdb.username,
		"ssm.tx.couchdb.password" to localDockerCompose.couchdb.password,
		"ssm.tx.couchdb.serviceName" to localDockerCompose.couchdb.serviceName
	)
}
