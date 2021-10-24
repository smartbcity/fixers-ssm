package ssm.sdk.client.repository

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId

class CommandArgs(
	val cmd: String,
	val fcn: String,
	val args: List<String>,
	val channelId: ChannelId?,
	val chaincodeId: ChaincodeId?,
)
