package ssm.couchdb.f2.query

import ssm.couchdb.dsl.config.CouchdbConfig

object TestConfig {
	val dbConfig = CouchdbConfig(
		url = "http://localhost:5984",
		username = "couchdb",
		password = "couchdb",
		serviceName = "ssm-couchdb-unit"
	)
	const val CHANNEL_ID = "sandbox"
	const val CHAINCODE_ID = "ssm"
}
