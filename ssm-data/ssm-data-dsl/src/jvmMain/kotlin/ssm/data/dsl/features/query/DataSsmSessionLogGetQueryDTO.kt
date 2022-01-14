package ssm.data.dsl.features.query

import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.chaincode.dsl.model.uri.SsmUriDTO
import ssm.data.dsl.model.DataSsmSessionStateDTO

actual interface DataSsmSessionLogGetQueryDTO : DataQueryDTO {
	actual val sessionName: SessionName
	actual val txId: TransactionId
	actual override val ssmUri: SsmUriDTO
}

actual interface DataSsmSessionLogGetQueryResultDTO {
	actual val item: DataSsmSessionStateDTO?
}
