package ssm.data.spring.autoconfigure

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.data.dsl.config.DataSsmConfig

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "ssm")
class CucumberTests

object TestConfiguration {
	private val localDockerCompose = DataSsmConfig(
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
		"ssm.data.chaincode.url" to localDockerCompose.chaincode.url,
		"ssm.data.couchdb.url" to localDockerCompose.couchdb.url,
		"ssm.data.couchdb.username" to localDockerCompose.couchdb.username,
		"ssm.data.couchdb.password" to localDockerCompose.couchdb.password,
		"ssm.data.couchdb.serviceName" to localDockerCompose.couchdb.serviceName
	)
}
