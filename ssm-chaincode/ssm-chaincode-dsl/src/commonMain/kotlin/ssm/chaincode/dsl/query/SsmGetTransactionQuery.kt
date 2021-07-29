package ssm.chaincode.dsl.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmCommandDTO
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionId
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetTransactionQueryFunction = F2Function<SsmGetTransactionQuery, SsmGetTransactionQueryResult>

@Serializable
@JsExport
@JsName("SsmGetTransactionQuery")
data class SsmGetTransactionQuery(
    val id: TransactionId,
    override val baseUrl: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?
): SsmCommandDTO

@Serializable
@JsExport
@JsName("SsmGetTransactionQueryResult")
class SsmGetTransactionQueryResult(
    val transaction: Transaction?
)