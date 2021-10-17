package ssm.chaincode.dsl.model.uri

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.SsmName

actual interface ChaincodeUriBurstDTO {
	actual val peerId: PeerId?
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
}
