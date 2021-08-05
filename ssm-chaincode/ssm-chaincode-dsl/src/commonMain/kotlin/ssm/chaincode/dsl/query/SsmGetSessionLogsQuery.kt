package ssm.chaincode.dsl.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmCommandDTO
import ssm.chaincode.dsl.SsmSessionState
import ssm.chaincode.dsl.SsmSessionStateLog
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * Retrieves the logs of a session since its creation
 * @d2 function
 * @parent [ssm.chaincode.dsl.SsmSession]
 * @title Get Session Logs
 * @order 20
 */
typealias SsmGetSessionLogsQueryFunction = F2Function<SsmGetSessionLogsQuery, SsmGetSessionLogsQueryResult>

/**
 * @d2 query
 * @parent [SsmGetSessionLogsQueryFunction]
 * @title Get Session Logs: Parameters
 */
@Serializable
@JsExport
@JsName("SsmGetSessionLogsQuery")
class SsmGetSessionLogsQuery(
    /**
     * Identifier of the session to retrieve
     * @example [SsmSessionState.session]
     */
    val session: String,
    override val baseUrl: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?
): SsmCommandDTO

/**
 * @d2 event
 * @parent [SsmGetSessionLogsQueryFunction]
 * @title Get Session Logs: Result
 */
@Serializable
@JsExport
@JsName("SsmGetSessionLogsQueryResult")
data class SsmGetSessionLogsQueryResult(
    /**
     * The logs of the session since its creation
     * @example [{
     *  txId: "aa4659ab-6f4e-457c-8c75-ced489871a65",
     *  state: {
     *      ssm: "ProductLogistic",
     *      session: "eca7c042-ec37-489b-adb8-42c73ddcfb0b",
     *      roles: {
     *          Provider: "JohnDeuf",
     *          Seller: "BenEfficiere",
     *          Buyer: "JeanneAlyztou"
     *      },
     *      public: "",
     *      origin: {
     *          from: 0,
     *          to: 1,
     *          role: "Provider",
     *          action: "Build"
     *      },
     *      current: 1,
     *      iteration: 1
     *  }
     * }]
     */
    val logs: List<SsmSessionStateLog>
)