package ssm.data.dsl.config

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.couchdb.dsl.config.SsmCouchdbConfig

/**
 * @d2 model
 * @title SSM Configuration
 * @parent [ssm.data.dsl.SsmApiQueryFunctions]
 */
data class SsmDataConfig(
	/**
	 * Configuration for couchdb.
	 */
	val couchdb: SsmCouchdbConfig,
	/**
	 *  Configuration for couchdb.
	 */
	val chaincode: SsmChaincodeConfig,

)
