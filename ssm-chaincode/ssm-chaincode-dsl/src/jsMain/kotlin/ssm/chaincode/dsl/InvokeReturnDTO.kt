package ssm.chaincode.dsl

@JsExport
@JsName("InvokeReturnDTO")
actual external interface InvokeReturnDTO {
	actual val status: String
	actual val info: String
	actual val transactionId: String
}
