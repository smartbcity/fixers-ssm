package ssm.chaincode.dsl

@JsExport
@JsName("SsmAgentDTO")
actual external interface SsmAgentDTO {
	actual val name: String
	actual val pub: ByteArray
}