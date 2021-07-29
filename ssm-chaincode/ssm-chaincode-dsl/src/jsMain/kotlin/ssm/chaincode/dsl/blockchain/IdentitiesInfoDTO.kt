package ssm.chaincode.dsl.blockchain

@JsExport
@JsName("IdentitiesInfoDTO")
actual external interface IdentitiesInfoDTO {
	actual val id: String
	actual val mspid: String
}