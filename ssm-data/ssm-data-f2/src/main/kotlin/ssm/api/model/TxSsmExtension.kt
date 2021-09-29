package ssm.api.model

import ssm.chaincode.dsl.model.Ssm
import ssm.data.dsl.model.TxChannel
import ssm.data.dsl.model.DataSsm

fun Ssm.toDataSsm(): DataSsm {
	return DataSsm(
		ssm = this,
		version = "Not implemented",
		channel = TxChannel("Not implemented")
	)
}
