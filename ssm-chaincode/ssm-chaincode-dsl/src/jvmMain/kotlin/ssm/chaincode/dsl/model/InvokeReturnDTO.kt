package ssm.chaincode.dsl.model

actual interface InvokeReturnDTO {
	actual val status: String
	actual val info: String
	actual val transactionId: String
}
