package ssm.tx.dsl.model

import ssm.chaincode.dsl.model.SsmAgentDTO

@JsExport
@JsName("TxSsmUserDTO")
actual external interface TxSsmUserDTO {
	actual val agent: SsmAgentDTO
}
