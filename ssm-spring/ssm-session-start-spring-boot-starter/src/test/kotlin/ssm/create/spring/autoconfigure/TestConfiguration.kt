package ssm.create.spring.autoconfigure

import io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.config.SsmChaincodeConfig

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ssm")
class CucumberTests

object SsmChaincodeConfigTest {
	val localDockerCompose = SsmChaincodeConfig(
		url = "http://localhost:9090"
	)

	val localDockerComposeParams = mapOf(
		"ssm.chaincode.url" to localDockerCompose.url
	)
}
