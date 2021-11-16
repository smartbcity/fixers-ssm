package ssm.data.dsl.model

import ssm.chaincode.dsl.model.SsmAgentDTO

@JsExport
@JsName("DataSsmUserDTO")
actual external interface DataSsmUserDTO {
	actual val agent: SsmAgentDTO
}
