package ssm.chaincode.dsl

import ssm.chaincode.dsl.blockchain.TransactionId

actual interface SsmSessionStateLogDTO {
	actual val txId: TransactionId
	actual val state: SsmSessionStateDTO
}
