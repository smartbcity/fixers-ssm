package x2.api.ssm.api.model

import ssm.dsl.SsmSessionState
import x2.api.ssm.model.SsmSessionBase
import x2.api.ssm.model.SsmTransactionBase
import x2.api.ssm.model.SsmUserBase

fun SsmSessionState.toSession(firstTransaction: ssm.dsl.blockchain.Transaction?, lastTransaction: ssm.dsl.blockchain.Transaction?): SsmSessionBase {
    return SsmSessionBase(
        id = this.session,
        channel = "Not implemented",
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
