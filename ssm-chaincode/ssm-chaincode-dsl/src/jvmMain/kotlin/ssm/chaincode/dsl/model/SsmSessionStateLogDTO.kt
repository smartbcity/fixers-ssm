package ssm.chaincode.dsl.model

import ssm.chaincode.dsl.blockchain.TransactionId

actual interface SsmSessionStateLogDTO {
	actual val txId: TransactionId
	actual val state: SsmSessionStateDTO
}
