package ssm.data.dsl.features.query

import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.data.dsl.model.DataSsmSessionStateDTO

@JsExport
@JsName("DataSsmSessionLogGetQueryDTO")
actual external interface DataSsmSessionLogGetQueryDTO : DataQueryDTO {
	actual val sessionName: SessionName
	actual val txId: TransactionId
	actual override val ssmUri: SsmUri
}

@JsExport
@JsName("DataSsmSessionLogGetQueryResultDTO")
actual external interface DataSsmSessionLogGetQueryResultDTO {
	actual val item: DataSsmSessionStateDTO?
}
