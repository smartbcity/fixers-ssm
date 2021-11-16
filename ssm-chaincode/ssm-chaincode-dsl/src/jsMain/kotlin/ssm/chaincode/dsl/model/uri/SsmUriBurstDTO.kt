package ssm.chaincode.dsl.model.uri

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.SsmName

@JsExport
@JsName("SsmUriBurstDTO")
actual external interface SsmUriBurstDTO {
	actual val peerId: PeerId?
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
	actual val ssmName: SsmName
	actual val ssmVersion: SsmVersion
}
