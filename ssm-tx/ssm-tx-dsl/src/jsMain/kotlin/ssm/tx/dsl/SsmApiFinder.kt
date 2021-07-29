package ssm.tx.dsl

import ssm.tx.dsl.features.query.*

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
