package x2.api.ssm.api.model

import TxChannelBase
import TxSsmBase
import ssm.chaincode.dsl.SsmBase

fun SsmBase.toTxSsm(): TxSsmBase {
    return TxSsmBase(
        ssm = this,
        version = "Not implemented",
        channel = TxChannelBase("Not implemented")
    )
}