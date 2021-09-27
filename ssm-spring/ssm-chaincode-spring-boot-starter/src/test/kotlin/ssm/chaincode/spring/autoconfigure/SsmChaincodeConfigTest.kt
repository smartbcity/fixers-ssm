package ssm.chaincode.spring.autoconfigure

import ssm.chaincode.dsl.config.SsmChaincodeConfig

object SsmChaincodeConfigTest {
	val localDockerCompose = SsmChaincodeConfig(
		url = "http://localhost:9090"
	)
}
