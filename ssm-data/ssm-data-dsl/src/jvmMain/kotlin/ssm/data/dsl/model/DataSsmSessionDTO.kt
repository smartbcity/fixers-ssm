package ssm.data.dsl.model

import ssm.chaincode.dsl.blockchain.TransactionDTO

actual interface DataSsmSessionDTO {
	actual val id: DataSsmSessionId
	actual val state: DataSsmSessionStateDTO
	actual val channel: DataChannelDTO
	actual val transaction: TransactionDTO?
}
