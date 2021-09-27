package ssm.chaincode.dsl.model

@JsExport
@JsName("InvokeReturnDTO")
actual external interface InvokeReturnDTO {
	actual val status: String
	actual val info: String
	actual val transactionId: String
}
