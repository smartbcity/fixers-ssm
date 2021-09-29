package ssm.data.dsl.model

import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.blockchain.TransactionDTO

@JsExport
@JsName("DataSsmSessionStateDTO")
actual external interface DataSsmSessionStateDTO {
	actual val details: SsmSessionStateDTO
	actual val transaction: TransactionDTO?
}
