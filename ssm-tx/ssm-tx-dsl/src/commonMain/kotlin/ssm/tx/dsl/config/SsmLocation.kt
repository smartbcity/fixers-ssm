package ssm.tx.dsl.config

import ssm.chaincode.dsl.ChaincodeId
import ssm.chaincode.dsl.ChannelId
import ssm.chaincode.dsl.Ssm
import ssm.couchdb.dsl.config.CouchdbConfig
import ssm.tx.dsl.features.query.SsmName
import ssm.tx.dsl.model.SsmVersion

/**
 * Defines SSM configurations associated by name and version.
 * @d2 model
 * @title SSM Configuration Map
 * @order 10
 * @example {
 *  "ProductLogistic": {
 *      "1.0": {
 *          "baseUrl": "http://peer.sandbox.smartb.network:9000",
 *          "channel": "channel-smartb",
 *          "chaincode": "ssm-smartb",
 *          "couchdb": "smartbase",
 *          "dbName": "smartbase_ssm"
 *      }
 *  }
 * }
 */
typealias TxSsmConfig = Map<SsmName, Map<SsmVersion, TxSsmLocationProperties>>

/**
 * @d2 model
 * @title SSM Configuration
 * @parent [TxSsmConfig]
 */
data class TxSsmLocationProperties(
	val couchdb: CouchdbConfig,
	/**
	 * The unique id of a chaincode.
	 */
	val chaincodeId: ChaincodeId,
	/**
	 * The unique id of a channel.
	 */
	val channelId: ChannelId,
)
