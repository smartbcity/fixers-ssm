package ssm.api.model

import ssm.tx.dsl.model.TxSsmSession
import ssm.tx.dsl.model.TxSsmSessionState
import ssm.chaincode.dsl.SsmSessionStateBase
import ssm.chaincode.dsl.blockchain.TransactionBase
import ssm.tx.dsl.model.TxChannel

fun SsmSessionStateBase.toTxSession(firstTransaction: TransactionBase?, lastTransaction: TransactionBase?): TxSsmSession {
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
