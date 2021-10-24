package ssm.data.bdd

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.data.dsl.config.SsmDataConfig

object TestConfig {

	val local = SsmDataConfig(
		couchdb = SsmCouchdbConfig(
			url = "http://localhost:5984",
			username = "admin",
			password = "admin",
			serviceName = "ssm-data-unit"
		),
		chaincode = SsmChaincodeConfig(
			url = "http://localhost:9090"
		),
	)
	val proudhon = SsmDataConfig(
		couchdb = SsmCouchdbConfig(
			url = "http://peer0.pr-commune.smartb.network:5984",
			username = "1111",
			password = "2222",
			serviceName = "ssm-couchdb-unit"
		),
		chaincode = SsmChaincodeConfig(
			url = "http://peer0.pr-commune.smartb.network"
		),
	)
}
