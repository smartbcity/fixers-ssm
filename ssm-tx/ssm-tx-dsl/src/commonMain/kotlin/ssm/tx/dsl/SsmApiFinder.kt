package ssm.tx.dsl

import ssm.tx.dsl.features.query.*
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface SsmApiFinder {
	fun txSsmListQueryFunction(): TxSsmListQueryFunction
	fun txSsmGetQueryFunction(): TxSsmGetQueryFunction
	fun txSsmSessionListQueryFunction(): TxSsmSessionListQueryFunction
	fun txSsmSessionGetQueryFunction(): TxSsmSessionGetQueryFunction
	fun txSsmSessionLogListQueryFunction(): TxSsmSessionLogListQueryFunction
	fun txSsmSessionLogGetQueryFunction(): TxSsmSessionLogGetQueryFunction
}
