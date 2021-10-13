package ssm.chaincode.client.repository

data class CommandArgs(
	val cmd: String,
	val fcn: String,
	val args: List<String>,
	val channelid: String?,
	val chaincodeid: String?,
)
