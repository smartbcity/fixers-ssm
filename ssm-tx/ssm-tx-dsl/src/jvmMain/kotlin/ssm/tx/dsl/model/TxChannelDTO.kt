package ssm.tx.dsl.model

import ssm.chaincode.dsl.ChannelId

actual interface TxChannelDTO {
	actual val id: ChannelId
}
