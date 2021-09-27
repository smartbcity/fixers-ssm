package ssm.couchdb.autoconfig

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.tx.dsl.config.SsmTxConfig

object SsmTxConfigTest {
	val localDockerCompose = SsmTxConfig(
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
}
