package ssm.chaincode.dsl.model

@JsExport
@JsName("SsmDTO")
actual external interface SsmDTO {
	actual val name: String
	actual val transitions: List<SsmTransitionDTO>
}
