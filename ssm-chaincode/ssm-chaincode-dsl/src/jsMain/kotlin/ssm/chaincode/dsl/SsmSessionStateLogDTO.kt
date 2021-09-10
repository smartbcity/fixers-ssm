package ssm.chaincode.dsl

import ssm.chaincode.dsl.blockchain.TransactionId

@JsExport
@JsName("SsmSessionStateLogDTO")
actual external interface SsmSessionStateLogDTO {
	actual val txId: TransactionId
	actual val state: SsmSessionStateDTO
}
