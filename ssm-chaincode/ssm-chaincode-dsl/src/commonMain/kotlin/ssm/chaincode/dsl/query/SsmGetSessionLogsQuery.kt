package ssm.chaincode.dsl.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.chaincode.dsl.SsmCommand
import ssm.chaincode.dsl.SsmSessionStateLog
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetSessionLogsQueryFunction = F2Function<SsmGetSessionLogsQuery, SsmGetSessionLogsQueryResult>
typealias SsmGetSessionLogsQueryFunctionRemote = F2FunctionRemote<SsmGetSessionLogsQuery, SsmGetSessionLogsQueryResult>

@JsExport
@JsName("SsmGetSessionLogsQuery")
class SsmGetSessionLogsQuery(
    val session: String,
    override val baseUrl: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?
): SsmCommand

@JsExport
@JsName("SsmGetSessionLogsQueryResult")
data class SsmGetSessionLogsQueryResult(
     val logs: List<SsmSessionStateLog>
)