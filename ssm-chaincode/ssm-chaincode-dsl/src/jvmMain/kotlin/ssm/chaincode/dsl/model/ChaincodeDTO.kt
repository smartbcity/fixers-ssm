package ssm.chaincode.dsl.model

actual interface ChaincodeDTO {
	actual val id: ChaincodeId
	actual val channelId: ChannelId
}
