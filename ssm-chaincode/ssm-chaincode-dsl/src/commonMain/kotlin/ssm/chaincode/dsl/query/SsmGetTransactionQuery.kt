package ssm.chaincode.dsl.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.chaincode.dsl.SsmCommand
import ssm.chaincode.dsl.blockchain.TransactionBase
import ssm.chaincode.dsl.blockchain.TransactionId
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetTransactionQueryFunction = F2Function<SsmGetTransactionQuery, SsmGetTransactionQueryResult>
typealias SsmGetTransactionQueryFunctionRemote = F2FunctionRemote<SsmGetTransactionQuery, SsmGetTransactionQueryResult>

@JsExport
@JsName("SsmGetTransactionQuery")
class SsmGetTransactionQuery(
    val id: TransactionId,
    override val baseUrl: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?
): SsmCommand

@JsExport
@JsName("SsmGetTransactionQueryResult")
class SsmGetTransactionQueryResult(
    val transaction: TransactionBase?
)