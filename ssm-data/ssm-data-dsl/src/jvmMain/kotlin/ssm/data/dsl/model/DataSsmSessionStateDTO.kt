package ssm.data.dsl.model

import ssm.chaincode.dsl.blockchain.TransactionDTO
import ssm.chaincode.dsl.model.SsmSessionStateDTO

actual interface DataSsmSessionStateDTO {
	actual val details: SsmSessionStateDTO
	actual val transaction: TransactionDTO?
}
