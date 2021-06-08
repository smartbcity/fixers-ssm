package x2.api.ssm.api.model

import x2.api.ssm.domain.model.SsmBase
import ssm.dsl.SsmTransition
import x2.api.ssm.domain.model.TxChannelBase

fun ssm.dsl.Ssm.toSsm(): SsmBase {
    return SsmBase(
        ssm = this,
        version = "Not implemented",
        channel = TxChannelBase( "Not implemented")
    )
}