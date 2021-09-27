package ssm.tx.dsl.model

import ssm.chaincode.dsl.model.SsmAgentDTO

actual interface TxSsmUserDTO {
	actual val agent: SsmAgentDTO
}
