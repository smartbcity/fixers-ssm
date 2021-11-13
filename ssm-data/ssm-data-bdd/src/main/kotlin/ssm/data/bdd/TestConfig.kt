package ssm.data.bdd

import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.couchdb.dsl.config.CouchdbSsmConfig
import ssm.data.dsl.config.DataSsmConfig

object TestConfig {

	val local = DataSsmConfig(
		couchdb = CouchdbSsmConfig(
			url = "http://localhost:5984",
			username = "couchdb",
			password = "couchdb",
			serviceName = "ssm-data-unit"
		),
		chaincode = ChaincodeSsmConfig(
			url = "http://localhost:9090"
		),
	)
	val proudhon = DataSsmConfig(
		couchdb = CouchdbSsmConfig(
			url = "http://peer0.pr-commune.smartb.network:5984",
			username = "1111",
			password = "2222",
			serviceName = "ssm-couchdb-unit"
		),
		chaincode = ChaincodeSsmConfig(
			url = "http://peer0.pr-commune.smartb.network"
		),
	)
}
