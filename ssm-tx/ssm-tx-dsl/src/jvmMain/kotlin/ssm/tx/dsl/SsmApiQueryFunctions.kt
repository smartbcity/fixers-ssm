package ssm.tx.dsl

import ssm.tx.dsl.config.SsmTxConfig
import ssm.tx.dsl.features.query.TxSsmGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogListQueryFunction

actual interface SsmApiQueryFunctions {
	actual fun txSsmListQueryFunction(config: SsmTxConfig): TxSsmListQueryFunction
	actual fun txSsmGetQueryFunction(config: SsmTxConfig): TxSsmGetQueryFunction
	actual fun txSsmSessionListQueryFunction(config: SsmTxConfig): TxSsmSessionListQueryFunction
	actual fun txSsmSessionGetQueryFunction(config: SsmTxConfig): TxSsmSessionGetQueryFunction
	actual fun txSsmSessionLogListQueryFunction(config: SsmTxConfig): TxSsmSessionLogListQueryFunction
	actual fun txSsmSessionLogGetQueryFunction(config: SsmTxConfig): TxSsmSessionLogGetQueryFunction
}
