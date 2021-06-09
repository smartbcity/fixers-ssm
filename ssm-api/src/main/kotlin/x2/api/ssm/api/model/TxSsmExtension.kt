package x2.api.ssm.api.model

import ssm.dsl.Ssm
import x2.api.ssm.domain.model.TxChannelBase
import x2.api.ssm.domain.model.TxSsmBase

fun Ssm.toSsm(): TxSsmBase {
    return TxSsmBase(
        ssm = this,
        version = "Not implemented",
        channel = TxChannelBase( "Not implemented")
    )
}