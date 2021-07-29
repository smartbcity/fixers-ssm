package ssm.tx.dsl

import ssm.tx.dsl.features.query.*
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmApiFinder")
interface SsmApiFinder {
	@JsName("txSsmListQueryFunction")
	fun txSsmListQueryFunction(): TxSsmListQueryFunction

	@JsName("txSsmGetQueryFunction")
	fun txSsmGetQueryFunction(): TxSsmGetQueryFunction

	@JsName("txSsmSessionListQueryFunction")
	fun txSsmSessionListQueryFunction(): TxSsmSessionListQueryFunction

	@JsName("txSsmSessionGetQueryFunction")
	fun txSsmSessionGetQueryFunction(): TxSsmSessionGetQueryFunction

	@JsName("txSsmSessionLogListQueryFunction")
	fun txSsmSessionLogListQueryFunction(): TxSsmSessionLogListQueryFunction

	@JsName("txSsmSessionLogGetQueryFunction")
	fun txSsmSessionLogGetQueryFunction(): TxSsmSessionLogGetQueryFunction
}

@JsExport
@JsName("SsmApiFinderClient")
interface SsmApiFinderClient {
	@JsName("txSsmListQueryFunction")
	fun txSsmListQueryFunction(): TxSsmListQueryRemoteFunction

	@JsName("txSsmGetQueryFunction")
	fun txSsmGetQueryFunction(): TxSsmGetQueryRemoteFunction

	@JsName("txSsmSessionListQueryFunction")
	fun txSsmSessionListQueryFunction(): TxSsmSessionListQueryRemoteFunction

	@JsName("txSsmSessionGetQueryFunction")
	fun txSsmSessionGetQueryFunction(): TxSsmSessionLogGetQueryRemoteFunction

	@JsName("txSsmSessionLogListQueryFunction")
	fun txSsmSessionLogListQueryFunction(): TxSsmSessionLogListQueryRemoteFunction

	@JsName("txSsmSessionLogGetQueryFunction")
	fun txSsmSessionLogGetQueryFunction(): TxSsmSessionLogGetQueryRemoteFunction
}
