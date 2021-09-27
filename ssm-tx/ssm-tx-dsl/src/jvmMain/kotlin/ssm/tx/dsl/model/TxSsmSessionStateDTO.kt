package ssm.tx.dsl.model

import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.blockchain.TransactionDTO

actual interface TxSsmSessionStateDTO {
	actual val details: SsmSessionStateDTO
	actual val transaction: TransactionDTO?
}
