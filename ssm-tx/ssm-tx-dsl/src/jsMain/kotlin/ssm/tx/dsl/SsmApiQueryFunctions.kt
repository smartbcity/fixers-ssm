package ssm.tx.dsl

import ssm.tx.dsl.config.SsmTxConfig
import ssm.tx.dsl.features.query.TxSsmGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogListQueryFunction

@JsExport
@JsName("SsmApiFinder")
actual external interface SsmApiQueryFunctions {
	@JsName("txSsmListQueryFunction")
	actual fun txSsmListQueryFunction(config: SsmTxConfig): TxSsmListQueryFunction

	@JsName("txSsmGetQueryFunction")
	actual fun txSsmGetQueryFunction(config: SsmTxConfig): TxSsmGetQueryFunction

	@JsName("txSsmSessionListQueryFunction")
	actual fun txSsmSessionListQueryFunction(config: SsmTxConfig): TxSsmSessionListQueryFunction

	@JsName("txSsmSessionGetQueryFunction")
	actual fun txSsmSessionGetQueryFunction(config: SsmTxConfig): TxSsmSessionGetQueryFunction

	@JsName("txSsmSessionLogListQueryFunction")
	actual fun txSsmSessionLogListQueryFunction(config: SsmTxConfig): TxSsmSessionLogListQueryFunction

	@JsName("txSsmSessionLogGetQueryFunction")
	actual fun txSsmSessionLogGetQueryFunction(config: SsmTxConfig): TxSsmSessionLogGetQueryFunction
}
