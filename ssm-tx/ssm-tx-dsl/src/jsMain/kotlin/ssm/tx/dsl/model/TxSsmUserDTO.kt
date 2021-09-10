package ssm.tx.dsl.model

import ssm.chaincode.dsl.SsmAgentDTO

@JsExport
@JsName("TxSsmUserDTO")
actual external interface TxSsmUserDTO {
	actual val agent: SsmAgentDTO
}
