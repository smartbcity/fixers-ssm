package ssm.tx.dsl.model

import ssm.chaincode.dsl.SsmAgentDTO

actual interface TxSsmUserDTO {
	actual val agent: SsmAgentDTO
}