package ssm.tx.dsl.model

import ssm.chaincode.dsl.SsmSessionStateDTO
import ssm.chaincode.dsl.blockchain.TransactionDTO

@JsExport
@JsName("TxSsmSessionStateDTO")
actual external interface TxSsmSessionStateDTO {
	actual val details: SsmSessionStateDTO
	actual val transaction: TransactionDTO?
}
