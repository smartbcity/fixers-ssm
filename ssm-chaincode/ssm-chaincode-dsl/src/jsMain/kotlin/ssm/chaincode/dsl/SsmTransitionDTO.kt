package ssm.chaincode.dsl

@JsExport
@JsName("SsmTransitionDTO")
actual external interface SsmTransitionDTO {
	actual val from: Int
	actual val to: Int
	actual val role: String
	actual val action: String
}