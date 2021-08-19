package ssm.chaincode.dsl

expect interface SsmChaincodePropertiesDTO {
    /**
     * URL of the peer hosting the chaincode
     * @example "http://peer.sandbox.smartb.network:9000"
     */
    val baseUrl: String

    /**
     * Identifier of the channel hosting the chaincode
     * @example "channel-smartb"
     */
    val channelId: String?

    /**
     * Identifier of the chaincode
     * @example "ssm-smartb"
     */
    val chaincodeId: String?
}

class SsmChaincodeProperties(
    override val baseUrl: String,
    override val channelId: String?,
    override val chaincodeId: String?
): SsmChaincodePropertiesDTO