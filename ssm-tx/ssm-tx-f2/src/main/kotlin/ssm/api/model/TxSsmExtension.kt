package ssm.api.model

import ssm.chaincode.dsl.model.Ssm
import ssm.tx.dsl.model.TxChannel
import ssm.tx.dsl.model.TxSsm

fun Ssm.toTxSsm(): TxSsm {
	return TxSsm(
		ssm = this,
		version = "Not implemented",
		channel = TxChannel("Not implemented")
	)
}
