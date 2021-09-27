package ssm.chaincode.dsl.model

actual interface SsmTransitionDTO {
	actual val from: Int
	actual val to: Int
	actual val role: String
	actual val action: String
}
