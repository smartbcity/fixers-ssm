package ssm.chaincode.dsl

@JsExport
@JsName("SsmChaincodePropertiesDTO")
actual external interface SsmChaincodePropertiesDTO {
    actual val baseUrl: String
    actual val channelId: String?
    actual val chaincodeId: String?
}
