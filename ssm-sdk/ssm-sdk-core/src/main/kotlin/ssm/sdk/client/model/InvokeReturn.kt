package ssm.sdk.client.model

import ssm.chaincode.dsl.blockchain.TransactionId

data class InvokeReturn(
	val status: String,
	val info: String,
	val transactionId: TransactionId,
)
