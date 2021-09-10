package ssm.tx.dsl

import ssm.tx.dsl.features.query.TxSsmGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogListQueryFunction

@JsExport
@JsName("SsmApiFinder")
actual external interface SsmApiFinder {
	@JsName("txSsmListQueryFunction")
	actual fun txSsmListQueryFunction(): TxSsmListQueryFunction

	@JsName("txSsmGetQueryFunction")
	actual fun txSsmGetQueryFunction(): TxSsmGetQueryFunction

	@JsName("txSsmSessionListQueryFunction")
	actual fun txSsmSessionListQueryFunction(): TxSsmSessionListQueryFunction

	@JsName("txSsmSessionGetQueryFunction")
	actual fun txSsmSessionGetQueryFunction(): TxSsmSessionGetQueryFunction

	@JsName("txSsmSessionLogListQueryFunction")
	actual fun txSsmSessionLogListQueryFunction(): TxSsmSessionLogListQueryFunction

	@JsName("txSsmSessionLogGetQueryFunction")
	actual fun txSsmSessionLogGetQueryFunction(): TxSsmSessionLogGetQueryFunction
}
