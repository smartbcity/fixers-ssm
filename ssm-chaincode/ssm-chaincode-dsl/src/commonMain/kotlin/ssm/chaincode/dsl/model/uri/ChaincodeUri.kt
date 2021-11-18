package ssm.chaincode.dsl.model.uri

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.SsmName

class ChaincodeUri(val uri: String) {

	companion object {
		const val PARTS = 3
		const val PREFIX = "chaincode"
	}

	private val burst = uri.split(":")
	init {
		require(burst.size == PARTS)
		require(burst.first() == PREFIX)
	}

	val channelId
		get() = burst[1]
	val chaincodeId
		get() = burst[2]

}


fun ChaincodeUri.toSsmUri(ssmName: SsmName): SsmUri {
	return SsmUri.from(channelId, chaincodeId, ssmName)
}

fun ChaincodeUri.Companion.from(channelId: ChannelId, chaincodeId: ChaincodeId): ChaincodeUri {
	return ChaincodeUri("chaincode:$channelId:$chaincodeId")
}
