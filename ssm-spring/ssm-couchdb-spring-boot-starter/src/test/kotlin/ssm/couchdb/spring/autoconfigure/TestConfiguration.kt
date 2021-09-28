package ssm.couchdb.spring.autoconfigure

import io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.spring.autoconfigure.SsmChaincodeConfigTest.localDockerCompose

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ssm")
class CucumberTests

object SsmChaincodeConfigTest {
	val localDockerCompose = SsmCouchdbConfig(
		url = "http://localhost:5000",
		username = "couchdb",
		password = "couchdb",
		serviceName = "ssm-couchdb-test"
	)

	val localDockerComposeParams = mapOf(
		"ssm.couchdb.url" to localDockerCompose.url,
		"ssm.couchdb.username" to localDockerCompose.username,
		"ssm.couchdb.password" to localDockerCompose.password,
		"ssm.couchdb.serviceName" to localDockerCompose.serviceName
	)
}
