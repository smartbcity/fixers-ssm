package x2.api.ssm.api.model

import ssm.dsl.SsmSessionStateBase
import x2.api.ssm.domain.model.TxSsmSessionBase
import x2.api.ssm.domain.model.SsmTransactionBase
import x2.api.ssm.domain.model.SsmUserBase
import x2.api.ssm.domain.model.TxChannelBase

/**
 *
 */
fun SsmSessionStateBase.toSession(firstTransaction: ssm.dsl.blockchain.Transaction?, lastTransaction: ssm.dsl.blockchain.Transaction?): TxSsmSessionBase {
    return TxSsmSessionBase(
        state = this,
        channel = TxChannelBase("Not implemented"),
        creationDate = firstTransaction?.timestamp ?: 0,
        lastTransaction = SsmTransactionBase(
            id = lastTransaction?.transactionId ?: "",
            from = this.origin?.from ?: 0,
            to = this.origin?.to ?: 0,
            date = lastTransaction?.timestamp ?: 0,
            signer = SsmUserBase(
                name = lastTransaction?.creator?.mspid ?: "",
                publicKey = lastTransaction?.creator?.id ?: ""
            )
        )
    )
}
