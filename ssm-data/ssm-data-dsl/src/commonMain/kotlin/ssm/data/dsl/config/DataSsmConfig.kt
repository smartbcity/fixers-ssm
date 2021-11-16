package ssm.data.dsl.config

import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.couchdb.dsl.config.CouchdbSsmConfig

/**
 * @d2 model
 * @title SSM Configuration
 * @parent [ssm.data.dsl.SsmDataD2]
 */
data class DataSsmConfig(
	/**
	 * Configuration for couchdb.
	 */
	val couchdb: CouchdbSsmConfig,
	/**
	 *  Configuration for couchdb.
	 */
	val chaincode: ChaincodeSsmConfig,

	)
