package ssm.data.dsl.features.query

import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.data.dsl.model.DataSsmSessionId
import ssm.data.dsl.model.DataSsmSessionStateDTO

actual interface DataSsmSessionLogGetQueryDTO : DataQueryDTO {
	actual val sessionId: DataSsmSessionId
	actual val txId: TransactionId
	actual override val ssm: SsmName
	actual override val bearerToken: String?
}

actual interface DataSsmSessionLogGetQueryResultDTO {
	actual val ssmSessionState: DataSsmSessionStateDTO?
}
