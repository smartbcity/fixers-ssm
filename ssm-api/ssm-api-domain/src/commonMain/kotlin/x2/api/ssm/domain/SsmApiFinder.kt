package x2.api.ssm.domain

import GetSsmListQueryRemoteFunction
import GetSsmSessionListQueryRemoteFunction
import GetSsmSessionQueryRemoteFunction
import f2.dsl.function.F2FunctionRemote
import ssm.chaincode.dsl.SsmSessionStateLog
import ssm.chaincode.dsl.query.SsmGetQueryRemoteFunction
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
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
