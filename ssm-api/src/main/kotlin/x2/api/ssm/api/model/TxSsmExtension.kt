package x2.api.ssm.api.model

import TxChannelBase
import TxSsmBase
import ssm.dsl.Ssm

fun Ssm.toSsm(): TxSsmBase {
    return TxSsmBase(
        ssm = this,
        version = "Not implemented",
        channel = TxChannelBase("Not implemented")
    )
}