package ssm.tx.dsl.config

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.couchdb.dsl.config.SsmCouchdbConfig

/**
 * @d2 model
 * @title SSM Configuration
 * @parent [TxSsmConfig]
 */
data class SsmTxConfig(
	/**
	 * Configuration for couchdb.
	 */
	val couchdb: SsmCouchdbConfig,
	/**
	 *  Configuration for couchdb.
	 */
	val chaincode: SsmChaincodeConfig,
)
