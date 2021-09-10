package ssm.chaincode.dsl

@JsExport
@JsName("SsmDTO")
actual external interface SsmDTO {
	actual val name: String
	actual val transitions: List<SsmTransitionDTO>
}
