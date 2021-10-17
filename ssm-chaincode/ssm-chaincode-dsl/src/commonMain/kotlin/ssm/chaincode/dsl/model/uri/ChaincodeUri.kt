package ssm.chaincode.dsl.model.uri

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.SsmName

private const val WITH_PEER = 3
private const val WITHOUT_PEER = 2

typealias ChaincodeUri = String

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

fun Flow<ChaincodeUri>.burst(): Flow<ChaincodeUriBurstDTO?> = map { uri ->
	uri.burstChaincode()
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
