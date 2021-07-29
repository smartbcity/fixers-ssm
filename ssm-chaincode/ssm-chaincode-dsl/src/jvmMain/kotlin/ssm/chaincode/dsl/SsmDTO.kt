package ssm.chaincode.dsl

actual interface SsmDTO {
	actual val name: String
	actual val transitions: List<SsmTransitionDTO>
}