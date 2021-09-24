package ssm.api.model

import ssm.chaincode.dsl.SsmSessionState
import ssm.chaincode.dsl.SsmSessionStateDTO
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.tx.dsl.model.TxChannel
import ssm.tx.dsl.model.TxSsmSession
import ssm.tx.dsl.model.TxSsmSessionState

fun SsmSessionStateDTO.toTxSession(firstTransaction: Transaction?, lastTransaction: Transaction?): TxSsmSession {
	return TxSsmSession(
		id = this.session,
		state = TxSsmSessionState(
			details = this as SsmSessionState,
			transaction = lastTransaction
		),
		channel = TxChannel("Not implemented"),
		transaction = firstTransaction
	)
}
