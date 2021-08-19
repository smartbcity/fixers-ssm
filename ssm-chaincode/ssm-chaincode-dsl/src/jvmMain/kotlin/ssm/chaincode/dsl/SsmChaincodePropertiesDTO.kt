package ssm.chaincode.dsl

actual interface SsmChaincodePropertiesDTO {
    actual val baseUrl: String
    actual val channelId: String?
    actual val chaincodeId: String?
}
