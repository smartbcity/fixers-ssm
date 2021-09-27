package ssm.chaincode.dsl.model

import ssm.chaincode.dsl.model.SsmTransitionDTO

@JsExport
@JsName("SsmDTO")
actual external interface SsmDTO {
	actual val name: String
	actual val transitions: List<SsmTransitionDTO>
}
