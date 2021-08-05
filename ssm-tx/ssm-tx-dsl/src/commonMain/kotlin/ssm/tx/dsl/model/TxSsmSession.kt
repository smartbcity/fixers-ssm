package ssm.tx.dsl.model

import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.chaincode.dsl.blockchain.TransactionDTO
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface TxSsmSessionDTO {
    /**
     * Identifier of the session
     * @example [ssm.chaincode.dsl.SsmSession.session]
     */
    val id: TxSsmSessionId

    /**
     * The state of an SSM session
     */
    val state: TxSsmSessionStateDTO

    /**
     * The channel this session was instantiated in
     */
    val channel: TxChannelDTO

    /**
     * Transaction that initiated the state of the session
     */
    val transaction: TransactionDTO?
}

/**
 * @d2 model
 * @page
 * Represents an SSM session state with some metadata
 * @@title SSM-API/Session
 */
@Serializable
@JsExport
@JsName("TxSsmSession")
class TxSsmSession(
	override val id: TxSsmSessionId,
	override val state: TxSsmSessionState,
	override val channel: TxChannel,
	override val transaction: Transaction?,
): TxSsmSessionDTO

typealias TxSsmSessionId = String
