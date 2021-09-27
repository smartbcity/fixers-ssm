package ssm.chaincode.dsl.model

import ssm.chaincode.dsl.model.SsmTransitionDTO

actual interface SsmDTO {
	actual val name: String
	actual val transitions: List<SsmTransitionDTO>
}
