package ssm.chaincode.dsl.model

actual interface SsmDTO {
	actual val name: String
	actual val transitions: List<SsmTransitionDTO>
}
