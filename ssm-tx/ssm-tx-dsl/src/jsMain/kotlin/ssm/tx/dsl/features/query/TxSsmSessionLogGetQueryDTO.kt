package ssm.tx.dsl.features.query

import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionStateDTO

@JsExport
@JsName("TxSsmSessionLogGetQueryDTO")
actual external interface TxSsmSessionLogGetQueryDTO : TxQueryDTO {
	actual val sessionId: TxSsmSessionId
	actual val txId: TransactionId
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

@JsExport
@JsName("TxSsmSessionLogGetQueryResultDTO")
actual external interface TxSsmSessionLogGetQueryResultDTO {
	actual val ssmSessionState: TxSsmSessionStateDTO?
}
