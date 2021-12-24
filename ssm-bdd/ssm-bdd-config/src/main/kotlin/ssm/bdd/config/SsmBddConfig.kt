package ssm.bdd.config

import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.couchdb.dsl.config.CouchdbSsmConfig
import ssm.data.dsl.config.DataSsmConfig

object SsmBddConfig {

	object Chaincode {
		val url: String
			get() {
				return "http://localhost:9090"
			}
		val chaincodeUri: ChaincodeUri
			get() {
				return ChaincodeUri("chaincode:sandbox:ssm")
			}
		val config: ChaincodeSsmConfig
			get() {
				return ChaincodeSsmConfig(url = url)
			}
	}

	object Data {
		val config = DataSsmConfig(
			couchdb = Couchdb.config,
			chaincode = Chaincode.config,
		)
	}

	object Couchdb {
		val url: String
			get() {
				return "http://localhost:5984"
			}
		val username: String
			get() {
				return "couchdb"
			}
		val password: String
			get() {
				return "couchdb"
			}

		val config: CouchdbSsmConfig
			get() {
				return CouchdbSsmConfig(
					url = url,
					username = username,
					password = password,
					serviceName = "ssm-couchdb-test"
				)
			}
	}

	object Key{
		val admin: Pair<String, String>
			get() {
				return "ssm-admin" to "local/admin/ssm-admin"
			}
		val user: Pair<String, String>
			get() {
				return "ssm-admin" to "local/admin/ssm-admin"
			}
	}
}
