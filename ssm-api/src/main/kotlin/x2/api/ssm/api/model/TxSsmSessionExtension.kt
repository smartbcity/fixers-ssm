package x2.api.ssm.api.model

import TxChannelBase
import TxSsmSessionBase
import TxSsmSessionStateBase
import ssm.chaincode.dsl.SsmSessionStateBase
import ssm.chaincode.dsl.blockchain.TransactionBase

fun SsmSessionStateBase.toTxSession(firstTransaction: TransactionBase?, lastTransaction: TransactionBase?): TxSsmSessionBase {
    return TxSsmSessionBase(
        id = this.session,
        currentState = TxSsmSessionStateBase(
            details = this,
            transaction = lastTransaction
        ),
        channel = TxChannelBase("Not implemented"),
        creationTransaction = firstTransaction
    )
}
