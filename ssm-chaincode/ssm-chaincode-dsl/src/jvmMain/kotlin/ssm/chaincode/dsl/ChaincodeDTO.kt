package ssm.chaincode.dsl

actual interface ChaincodeDTO {
	actual val id: ChaincodeId
	actual val channelId: ChannelId
}