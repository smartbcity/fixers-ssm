package ssm.chaincode.dsl.model.uri

import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.SsmName

typealias PeerId = String
typealias SsmVersion = String

typealias SsmUri = String

private const val WITH_PEER = 5
private const val WITHOUT_PEER = 4
const val DEFAULT_VERSION = "1.0.0"

fun SsmUriBurstDTO.compact(): SsmUri {
	return peerId?.let {
		"ssm:$peerId:$channelId:$chaincodeId:$ssmName"
	} ?: "ssm:$channelId:$chaincodeId:$ssmName"
}

fun SsmUri.burstSsmUri(): SsmUriBurstDTO? {
	val parts = split(":")
	return when (parts.size) {
		WITHOUT_PEER -> {
			val (_, channelId, chaincodeId, ssmName) = parts
			return SsmUriBurst(
				channelId = channelId,
				chaincodeId = chaincodeId,
				ssmName = ssmName,
				peerId = null,
				ssmVersion = DEFAULT_VERSION
			)
		}
		WITH_PEER -> {
			val (_, peerId, channelId, chaincodeId, ssmName) = parts
			return SsmUriBurst(
				peerId = peerId,
				channelId = channelId,
				chaincodeId = chaincodeId,
				ssmName = ssmName,
				ssmVersion = DEFAULT_VERSION
			)
		}
		else -> null
	}
}

expect interface SsmUriBurstDTO {
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

	/**
	 * The name of ssm
	 * @example "ssmsmartb"
	 */
	val ssmName: SsmName

	/**
	 * The version of the ssm
	 * @example "1.0.1"
	 */
	val ssmVersion: SsmVersion
}

data class SsmUriBurst(
	override val peerId: PeerId? = null,
	override val channelId: ChannelId,
	override val chaincodeId: ChaincodeId,
	override val ssmName: SsmName,
	override val ssmVersion: SsmVersion = DEFAULT_VERSION,
) : SsmUriBurstDTO
