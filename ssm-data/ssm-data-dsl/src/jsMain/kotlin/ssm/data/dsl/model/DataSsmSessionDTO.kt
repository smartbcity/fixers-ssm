package ssm.data.dsl.model

import ssm.chaincode.dsl.blockchain.TransactionDTO
import ssm.chaincode.dsl.model.uri.SsmUri

@JsExport
@JsName("DataSsmSessionDTO")
actual external interface DataSsmSessionDTO {
	actual val ssmUri: SsmUri
	actual val id: DataSsmSessionId
	actual val state: DataSsmSessionStateDTO
	actual val channel: DataChannelDTO
	actual val transaction: TransactionDTO?
	actual val transactions: List<TransactionDTO>
}
