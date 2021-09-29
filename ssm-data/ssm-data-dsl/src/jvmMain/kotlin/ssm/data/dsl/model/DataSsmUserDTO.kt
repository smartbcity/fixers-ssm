package ssm.data.dsl.model

import ssm.chaincode.dsl.model.SsmAgentDTO

actual interface DataSsmUserDTO {
	actual val agent: SsmAgentDTO
}
