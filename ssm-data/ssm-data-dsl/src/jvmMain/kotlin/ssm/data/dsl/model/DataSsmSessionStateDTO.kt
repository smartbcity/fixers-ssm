package ssm.data.dsl.model

import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.blockchain.TransactionDTO

actual interface DataSsmSessionStateDTO {
	actual val details: SsmSessionStateDTO
	actual val transaction: TransactionDTO?
}
