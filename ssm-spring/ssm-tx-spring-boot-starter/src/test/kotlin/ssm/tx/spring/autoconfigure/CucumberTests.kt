package ssm.tx.spring.autoconfigure

import io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import ssm.chaincode.dsl.config.SsmChaincodeConfig

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ssm.test.cucumber.steps")
class CucumberTests