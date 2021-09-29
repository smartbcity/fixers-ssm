package ssm.api.model

import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.blockchain.Transaction
import ssm.data.dsl.model.TxChannel
import ssm.data.dsl.model.DataSsmSession
import ssm.data.dsl.model.DataSsmSessionState

fun SsmSessionStateDTO.toTxSession(firstTransaction: Transaction?, lastTransaction: Transaction?): DataSsmSession {
	return DataSsmSession(
		id = this.session,
		state = DataSsmSessionState(
			details = this as SsmSessionState,
			transaction = lastTransaction
		),
		channel = TxChannel("Not implemented"),
		transaction = firstTransaction
	)
}
