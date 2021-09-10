package ssm.tx.dsl.features.query

import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.tx.dsl.model.TxSsmSessionId
import ssm.tx.dsl.model.TxSsmSessionStateDTO

actual interface TxSsmSessionLogGetQueryDTO : TxQueryDTO {
	actual val sessionId: TxSsmSessionId
	actual val txId: TransactionId
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

actual interface TxSsmSessionLogGetQueryResultDTO {
	actual val ssmSessionState: TxSsmSessionStateDTO?
}
