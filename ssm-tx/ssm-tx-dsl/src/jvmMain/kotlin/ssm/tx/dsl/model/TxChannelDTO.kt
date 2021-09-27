package ssm.tx.dsl.model

import ssm.chaincode.dsl.model.ChannelId

actual interface TxChannelDTO {
	actual val id: ChannelId
}
