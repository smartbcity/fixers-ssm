package ssm.chaincode.dsl.config

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

/**
 * The configuration needed for
 * @d2 model
 * @example "ssm"
 * @parent [ssm.chaincode.dsl.SsmChaincodeD2Model]
 */
@Serializable
@JsExport
@JsName("SsmChaincodePropertiesDTO")
interface SsmChaincodePropertiesDTO {
	/**
	 * URL of the peer hosting the chaincode
	 * @example "http://peer.sandbox.smartb.network:9000"
	 */
	val url: String
}

@Serializable
@JsExport
@JsName("ChaincodeSsmConfig")
class ChaincodeSsmConfig(
	override val url: String,
) : SsmChaincodePropertiesDTO
