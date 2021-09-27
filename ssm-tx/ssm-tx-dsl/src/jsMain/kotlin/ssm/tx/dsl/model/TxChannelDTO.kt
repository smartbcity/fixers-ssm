package ssm.tx.dsl.model

import ssm.chaincode.dsl.model.ChannelId

actual external interface TxChannelDTO {
	actual val id: ChannelId
}
