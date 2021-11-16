package ssm.chaincode.dsl.model.uri

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.SsmName

private const val WITH_PEER = 4
private const val WITHOUT_PEER = 3

typealias ChaincodeUri = String

fun ChaincodeUri.toSsmUri(ssmName: SsmName): SsmUri {
	return this.burstChaincode()!!.toSsmUri(ssmName)
}

fun ChaincodeUriBurstDTO.toSsmUri(ssmName: SsmName): SsmUri {
	return SsmUriBurst(
		peerId = peerId,
		channelId = channelId,
		chaincodeId = chaincodeId,
		ssmName = ssmName
	).compact()
}

fun ChaincodeUriBurstDTO.compact(): ChaincodeUri {
	return peerId?.let {
		"chaincode:$peerId:$channelId:$chaincodeId"
	} ?: "chaincode:$channelId:$chaincodeId"
}
fun ChaincodeUriBurstDTO.compact(ssmName: SsmName): SsmUri {
	return peerId?.let {
		"chaincode:$peerId:$channelId:$chaincodeId:${ssmName}"
	} ?: "chaincode:$channelId:$chaincodeId:${ssmName}"
}



fun ChaincodeUri.burstChaincode(): ChaincodeUriBurstDTO? {
	val part = split(":")
	return when {
		part.size == WITHOUT_PEER -> {
			val (_, channelId, chaincodeId) = part
			return ChaincodeUriBurst(
				channelId = channelId,
				chaincodeId = chaincodeId,
				peerId = null,
			)
		}
		part.size == WITH_PEER -> {
			val (_, peerId, channelId, chaincodeId) = part
			return ChaincodeUriBurst(
				peerId = peerId,
				channelId = channelId,
				chaincodeId = chaincodeId,
			)
		}
		else -> null
	}
}

expect interface ChaincodeUriBurstDTO {
	/**
	 * Identifier of the peer interacting with the channel.
	 * @example "channel-smartb"
	 */
	val peerId: PeerId?
	/**
	 * Identifier of the channel hosting the chaincode
	 * @example "channel-smartb"
	 */
	val channelId: ChannelId

	/**
	 * Identifier of the chaincode
	 * @example "ssmsmartb"
	 */
	val chaincodeId: ChaincodeId
}

data class ChaincodeUriBurst(
	override val peerId: PeerId? = null,
	override val channelId: ChannelId,
	override val chaincodeId: ChaincodeId,
) : ChaincodeUriBurstDTO
