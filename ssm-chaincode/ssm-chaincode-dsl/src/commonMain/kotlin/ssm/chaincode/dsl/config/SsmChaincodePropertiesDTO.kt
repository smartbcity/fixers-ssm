package ssm.chaincode.dsl.config

/**
 * The configuration needed for
 * @d2 model
 * @example "ssm"
 * @parent [ssm.chaincode.dsl.SsmChaincodeD2Model]
 */
expect interface SsmChaincodePropertiesDTO {
	/**
	 * URL of the peer hosting the chaincode
	 * @example "http://peer.sandbox.smartb.network:9000"
	 */
	val url: String
}

class ChaincodeSsmConfig(
	override val url: String,
) : SsmChaincodePropertiesDTO
