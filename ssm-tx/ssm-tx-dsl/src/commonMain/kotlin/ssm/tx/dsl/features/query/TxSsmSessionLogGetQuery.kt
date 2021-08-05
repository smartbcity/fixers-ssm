package ssm.tx.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionState
import ssm.tx.dsl.model.TxSsmSessionStateDTO
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * Retrieve the state of a session corresponding to a given transaction within the blockchain
 * @d2 function
 * @parent [ssm.tx.dsl.model.TxSsmSession]
 * @order 30
 * @title Get Session Log
 */
typealias TxSsmSessionLogGetQueryFunction = F2Function<TxSsmSessionLogGetQueryDTO, TxSsmSessionLogGetQueryResultDTO>

expect interface TxSsmSessionLogGetQueryDTO: TxQueryDTO {
	/**
	 * Identifier of the session to retrieve
	 * @example [ssm.tx.dsl.model.TxSsmSession.id]
	 */
    val sessionId: TxSsmSessionId

	/**
	 * Identifier of the transaction to retrieve
	 * @example [ssm.chaincode.dsl.blockchain.Transaction.transactionId]
	 */
	val txId: TransactionId
    override val ssm: SsmName
    override val bearerToken: String?
}

/**
 * @d2 query
 * @parent [TxSsmSessionLogGetQueryFunction]
 * @title Get Session Log: Parameters
 */
@Serializable
@JsExport
@JsName("TxSsmSessionLogGetQuery")
class TxSsmSessionLogGetQuery(
	override val sessionId: TxSsmSessionId,
	override val txId: TransactionId,
	override val ssm: SsmName,
	override val bearerToken: String?
): TxSsmSessionLogGetQueryDTO

expect interface TxSsmSessionLogGetQueryResultDTO{
	/**
	 * The retrieved session state and transaction
	 */
	val ssmSessionState: TxSsmSessionStateDTO?
}

/**
 * @d2 event
 * @parent [TxSsmSessionLogGetQueryFunction]
 * @title Get Session Log: Result
 */
@Serializable
@JsExport
@JsName("TxSsmSessionLogGetQueryResult")
class TxSsmSessionLogGetQueryResult(
	override val ssmSessionState: TxSsmSessionState?
): TxSsmSessionLogGetQueryResultDTO
