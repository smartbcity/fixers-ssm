package ssm.couchdb.f2.commons

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.uri.ChaincodeUri

fun chainCodeDbName(channelId: ChannelId, chaincodeId: ChaincodeId) = "${channelId}_$chaincodeId"

fun ChaincodeUri.chainCodeDbName(): String {
	return chainCodeDbName(channelId, chaincodeId)
}
