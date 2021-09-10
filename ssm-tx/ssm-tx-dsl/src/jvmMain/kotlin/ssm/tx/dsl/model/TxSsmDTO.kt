package ssm.tx.dsl.model

import ssm.chaincode.dsl.SsmDTO

actual interface TxSsmDTO {
	actual val ssm: SsmDTO
	actual val channel: TxChannelDTO
	actual val version: SsmVersion
}
