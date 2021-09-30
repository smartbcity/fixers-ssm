package ssm.chaincode.client.model

data class InvokeArgs(
	val fcn: String,
	val args: List<String>,
)
