package ssm.tx.dsl.config

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
    /**
     * URL of the peer to contact
     * @example "http://peer.sandbox.smartb.network:9000"
    */
    val baseUrl: String,

    /**
     * Identifier of the channel in which the SSM has been instantiated
     * @example "channel-smartb"
     */
    val channel: String,

    /**
     * Identifier of the chaincode in which the SSM has been instantiated
     * @example "ssm-smartb"
     */
    val chaincode: String,

    /**
     * Name of the couchdb configuration to use
     * @example "smartbase"
     */
    val couchdb: String,

    /**
     * Name of the database linked to the SSM
     * @example "smartbase_ssm"
     */
    val dbName: String
)
