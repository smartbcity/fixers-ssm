package ssm.chaincode.dsl.model

@JsExport
@JsName("SsmAgentDTO")
actual external interface SsmAgentDTO {
	actual val name: String
	actual val pub: ByteArray
}
