package ssm.chaincode.dsl.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmCommandDTO
import ssm.chaincode.dsl.SsmSessionStateLog
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetSessionLogsQueryFunction = F2Function<SsmGetSessionLogsQuery, SsmGetSessionLogsQueryResult>

@Serializable
@JsExport
@JsName("SsmGetSessionLogsQuery")
class SsmGetSessionLogsQuery(
    val session: String,
    override val baseUrl: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?
): SsmCommandDTO

@Serializable
@JsExport
@JsName("SsmGetSessionLogsQueryResult")
data class SsmGetSessionLogsQueryResult(
     val logs: List<SsmSessionStateLog>
)