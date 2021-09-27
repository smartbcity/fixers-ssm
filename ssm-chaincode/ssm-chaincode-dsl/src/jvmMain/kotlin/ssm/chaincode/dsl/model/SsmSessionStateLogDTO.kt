package ssm.chaincode.dsl.model

import ssm.chaincode.dsl.blockchain.TransactionId
import ssm.chaincode.dsl.model.SsmSessionStateDTO

actual interface SsmSessionStateLogDTO {
	actual val txId: TransactionId
	actual val state: SsmSessionStateDTO
}
