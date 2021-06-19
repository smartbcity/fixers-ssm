package ssm.tx.dsl.config

import ssm.tx.dsl.features.query.SsmName
import ssm.tx.dsl.model.SsmVersion

typealias TxSsmConfig = Map<SsmName, Map<SsmVersion, TxSsmLocationProperties>>

data class TxSsmLocationProperties(
    val baseUrl: String,
    val channel: String,
    val chaincode: String,
    val couchdb: String,
    val dbName: String
)
