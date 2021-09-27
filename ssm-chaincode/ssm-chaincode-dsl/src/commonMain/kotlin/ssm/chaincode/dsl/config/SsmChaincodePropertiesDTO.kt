package ssm.chaincode.dsl.config

expect interface SsmChaincodePropertiesDTO {
	/**
	 * URL of the peer hosting the chaincode
	 * @example "http://peer.sandbox.smartb.network:9000"
	 */
	val url: String
}

class SsmChaincodeConfig(
	override val url: String,
) : SsmChaincodePropertiesDTO
