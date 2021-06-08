package x2.api.ssm.domain

import f2.dsl.function.F2FunctionRemote
import ssm.dsl.SsmSessionStateLog
import ssm.dsl.query.SsmGetQueryRemoteFunction
import ssm.dsl.query.SsmGetSessionLogsQuery
import x2.api.ssm.domain.features.*
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SsmApiFinder")
interface SsmApiFinder {
	@JsName("getAllSsm")
	fun getAllSsm(): GetSsmListQueryRemoteFunction
	@JsName("getSsm")
	fun getSsm(): SsmGetQueryRemoteFunction
	@JsName("getAllSessions")
	fun getAllSessions(): GetSsmSessionListQueryRemoteFunction
	@JsName("getSession")
	fun getSession(): GetSsmSessionQueryRemoteFunction
	@JsName("getSessionLogs")
	fun getSessionLogs(): F2FunctionRemote<SsmGetSessionLogsQuery, Array<SsmSessionStateLog>>
}
