package sdk

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.data.dsl.config.DataSsmConfig

object TestConfig {

	fun config(vararg filter: String) = DataSsmConfig(
		couchdb = SsmCouchdbConfig(
			url = "http://localhost:5984",
			username = "couchdb",
			password = "couchdb",
			serviceName = "ssm-couchdb-unit"
		),
		chaincode = SsmChaincodeConfig(
			url = "http://localhost:9090"
		),
	)
	fun proudhon() = DataSsmConfig(
		couchdb = SsmCouchdbConfig(
			url = "http://peer0.pr-commune.smartb.network:5984",
			username = "1111",
			password = "2222",
			serviceName = "ssm-couchdb-unit"
		),
		chaincode = SsmChaincodeConfig(
			url = "http://peer0.pr-commune.smartb.network"
		),
//		filter = listOf(
//			"ssm:peer0:proudhon:ssm:account",
//			"ssm:peer0:proudhon:ssm:Delivery"
//		)
	)

	val filter: String = "ssm:sandbox:ssm:MobilityImpactSsm"

}
