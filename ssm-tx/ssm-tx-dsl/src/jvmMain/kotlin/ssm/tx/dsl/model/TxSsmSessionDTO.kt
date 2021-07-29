package ssm.tx.dsl.model

import ssm.chaincode.dsl.blockchain.TransactionDTO

actual interface TxSsmSessionDTO {
	actual val id: TxSsmSessionId
	actual val state: TxSsmSessionStateDTO
	actual val channel: TxChannelDTO
	actual val transaction: TransactionDTO?
}