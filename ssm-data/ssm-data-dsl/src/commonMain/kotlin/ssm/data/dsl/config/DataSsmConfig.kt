package ssm.data.dsl.config

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.data.dsl.model.SsmVersion

/**
 * @d2 model
 * @title SSM Configuration
 * @parent [ssm.data.dsl.SsmApiQueryFunctions]
 */
data class DataSsmConfig(
	/**
	 * Configuration for couchdb.
	 */
	val couchdb: SsmCouchdbConfig,
	/**
	 *  Configuration for couchdb.
	 */
	val chaincode: SsmChaincodeConfig,

	/**
	 * Identifier of the channel hosting the chaincode
	 * @example "channel-smartb"
	 */
	val channelId: ChannelId,

	/**
	 * Identifier of the chaincode
	 * @example "ssmsmartb"
	 */
	val chaincodeId: ChaincodeId,

	/**
	 * The version of the ssm
	 * @example "1.0.1"
	 */
	val ssmVersion: SsmVersion
)
