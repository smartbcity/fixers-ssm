package ssm.chaincode.dsl

@JsExport
@JsName("SsmGrantDTO")
actual external interface SsmGrantDTO {
	actual val user: String
	actual val iteration: Int
	actual val credits: Map<String, CreditDTO>
}

@JsExport
@JsName("CreditDTO")
actual external interface CreditDTO {
	actual val amount: Int
}
