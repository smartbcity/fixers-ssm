package ssm.tx.dsl.model

import ssm.chaincode.dsl.model.SsmDTO

@JsExport
@JsName("TxSsmDTO")
actual external interface TxSsmDTO {
	actual val ssm: SsmDTO
	actual val channel: TxChannelDTO
	actual val version: SsmVersion
}
