package ssm.chaincode.client.invoke.command

data class InvokeArgs(
    val fcn: String,
    val args: List<String>
)