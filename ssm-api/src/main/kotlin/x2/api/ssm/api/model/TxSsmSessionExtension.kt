package x2.api.ssm.api.model

import TxChannelBase
import TxSsmSessionBase
import TxSsmTransactionBase
import TxSsmUserBase
import ssm.dsl.SsmSessionStateBase
import ssm.dsl.blockchain.Transaction

fun SsmSessionStateBase.toSession(firstTransaction: Transaction?, lastTransaction: Transaction?): TxSsmSessionBase {
    return TxSsmSessionBase(
        state = this,
        channel = TxChannelBase("Not implemented"),
        creationDate = firstTransaction?.timestamp ?: 0,
        lastTransaction = TxSsmTransactionBase(
            id = lastTransaction?.transactionId ?: "",
            from = this.origin?.from ?: 0,
            to = this.origin?.to ?: 0,
            date = lastTransaction?.timestamp ?: 0,
            signer = TxSsmUserBase(
                name = lastTransaction?.creator?.mspid ?: "",
                publicKey = lastTransaction?.creator?.id ?: ""
            )
        )
    )
}
