package ssm.tx.dsl

import ssm.tx.dsl.features.query.*
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmApiFinder")
interface SsmApiFinder {
	@JsName("txSsmListQueryFunction")
	fun txSsmListQueryFunction(): TxSsmListQueryFunction

	@JsName("txSsmGetOneQueryFunction")
	fun txSsmGetOneQueryFunction(): TxSsmGetQueryFunction

	@JsName("txSsmSessionGetListQueryFunction")
	fun txSsmSessionGetListQueryFunction(): TxSsmSessionListQueryFunction

	@JsName("txSsmSessionGetOneQueryFunction")
	fun txSsmSessionGetOneQueryFunction(): TxSsmSessionGetOneQueryFunction

	@JsName("txSsmSessionLogGetListQueryFunction")
	fun txSsmSessionLogGetListQueryFunction(): TxSsmSessionLogListQueryFunction

	@JsName("txSsmSessionLogGetOneQueryFunction")
	fun txSsmSessionLogGetQueryFunction(): TxSsmSessionLogGetQueryFunction
}

@JsExport
@JsName("SsmApiFinderClient")
interface SsmApiFinderClient {
	@JsName("txSsmListQueryFunction")
	fun txSsmListQueryFunction(): TxSsmListQueryRemoteFunction

	@JsName("txSsmGetOneQueryFunction")
	fun txSsmGetOneQueryFunction(): TxSsmGetQueryRemoteFunction

	@JsName("txSsmSessionGetListQueryFunction")
	fun txSsmSessionGetListQueryFunction(): TxSsmSessionListQueryRemoteFunction

	@JsName("txSsmSessionGetOneQueryFunction")
	fun txSsmSessionGetOneQueryFunction(): TxSsmSessionLogGetQueryRemoteFunction

	@JsName("txSsmSessionLogGetListQueryFunction")
	fun txSsmSessionLogGetListQueryFunction(): TxSsmSessionLogListQueryRemoteFunction

	@JsName("txSsmSessionLogGetOneQueryFunction")
	fun txSsmSessionLogGetOneQueryFunction(): TxSsmSessionLogGetQueryRemoteFunction
}
