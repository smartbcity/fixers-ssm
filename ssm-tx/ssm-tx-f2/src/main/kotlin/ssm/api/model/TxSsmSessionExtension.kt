package ssm.api.model

import ssm.chaincode.dsl.SsmSessionState
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.tx.dsl.model.TxChannel
import ssm.tx.dsl.model.TxSsmSession
import ssm.tx.dsl.model.TxSsmSessionState

fun SsmSessionState.toTxSession(firstTransaction: Transaction?, lastTransaction: Transaction?): TxSsmSession {
	return TxSsmSession(
		id = this.session,
		state = TxSsmSessionState(
			details = this,
			transaction = lastTransaction
		),
		channel = TxChannel("Not implemented"),
		transaction = firstTransaction
	)
}
