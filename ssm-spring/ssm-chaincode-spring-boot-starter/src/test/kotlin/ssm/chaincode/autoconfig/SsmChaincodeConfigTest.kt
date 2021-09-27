package ssm.chaincode.autoconfig

import ssm.chaincode.dsl.config.SsmChaincodeConfig

object SsmChaincodeConfigTest {
	val localDockerCompose = SsmChaincodeConfig(
		url = "http://localhost:9090"
	)
}
