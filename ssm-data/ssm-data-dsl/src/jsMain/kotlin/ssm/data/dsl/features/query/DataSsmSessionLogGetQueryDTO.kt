package ssm.data.dsl.features.query

import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.data.dsl.model.DataSsmSessionId
import ssm.data.dsl.model.DataSsmSessionStateDTO

@JsExport
@JsName("DataSsmSessionLogGetQueryDTO")
actual external interface DataSsmSessionLogGetQueryDTO : ssm.data.dsl.features.query.DataQueryDTO {
	actual val sessionId: DataSsmSessionId
	actual val txId: TransactionId
	actual override val ssm: ssm.data.dsl.features.query.SsmName
	actual override val bearerToken: String?
}

@JsExport
@JsName("DataSsmSessionLogGetQueryResultDTO")
actual external interface DataSsmSessionLogGetQueryResultDTO {
	actual val ssmSessionState: DataSsmSessionStateDTO?
}
