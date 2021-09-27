package ssm.tx.dsl.model

import ssm.chaincode.dsl.ChannelId

actual external interface TxChannelDTO {
	actual val id: ChannelId
}
