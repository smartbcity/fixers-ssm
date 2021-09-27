package ssm.chaincode.dsl.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmChaincodeConfig
import ssm.chaincode.dsl.SsmCommandDTO
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionId

typealias SsmGetTransactionQueryFunction = F2Function<SsmGetTransactionQuery, SsmGetTransactionQueryResult>

@Serializable
@JsExport
@JsName("SsmGetTransactionQuery")
data class SsmGetTransactionQuery(
	val id: TransactionId,
	override val bearerToken: String?,
) : SsmCommandDTO

@Serializable
@JsExport
@JsName("SsmGetTransactionQueryResult")
class SsmGetTransactionQueryResult(
	val transaction: Transaction?,
)
