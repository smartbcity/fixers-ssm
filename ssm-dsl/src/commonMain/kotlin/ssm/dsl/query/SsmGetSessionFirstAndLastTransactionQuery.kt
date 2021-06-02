package ssm.dsl.query

import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import ssm.dsl.SsmCommand
import ssm.dsl.blockchain.Transaction
import kotlin.js.JsExport
import kotlin.js.JsName

typealias SsmGetSessionFirstAndLastTransactionQueryFunction = F2Function<SsmGetSessionFirstAndLastTransactionQuery, SsmGetSessionFirstAndLastTransactionQueryResult>
typealias SsmGetSessionFirstAndLastTransactionQueryFunctionRemote = F2FunctionRemote<SsmGetSessionFirstAndLastTransactionQuery, SsmGetSessionFirstAndLastTransactionQueryResult>

@JsExport
@JsName("SsmGetSessionFirstAndLastTransactionQuery")
class SsmGetSessionFirstAndLastTransactionQuery(
    val session: String,
    override val baseUrl: String,
    override val channelId: String?,
    override val chaincodeId: String?,
    override val bearerToken: String?
): SsmCommand

@JsExport
@JsName("SsmGetSessionFirstAndLastTransactionQueryResult")
class SsmGetSessionFirstAndLastTransactionQueryResult(
    val firstTransaction: Transaction?,
    val lastTransaction: Transaction?
)