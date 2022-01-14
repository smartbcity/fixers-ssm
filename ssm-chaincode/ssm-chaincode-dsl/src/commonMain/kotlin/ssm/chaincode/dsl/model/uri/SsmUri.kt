package ssm.chaincode.dsl.model.uri

import kotlin.js.JsExport
import kotlin.js.JsName
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.SsmName

typealias SsmVersion = String

const val DEFAULT_VERSION = "1.0.0"

@JsName("SsmUriDTO")
expect interface SsmUriDTO {
	val uri: String
}

fun SsmUriDTO.burst() = SsmUri(uri)

@JsExport
@JsName("SsmUri")
data class SsmUri(override val uri: String): SsmUriDTO {

	companion object {
		const val PARTS = 4
		const val PREFIX = "ssm"
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
	val ssmName
		get() = burst.get(index = 3)
	val ssmVersion
		get() = DEFAULT_VERSION

}

fun SsmUri.Companion.from(
		 channelId: ChannelId,
		 chaincodeId: ChaincodeId,
		 ssmName: SsmName,
) = SsmUri("${PREFIX}:$channelId:$chaincodeId:$ssmName")
