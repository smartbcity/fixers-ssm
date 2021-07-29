package ssm.chaincode.client.invoke.query

import ssm.chaincode.client.invoke.command.InvokeArgs

interface HasGet {
    fun queryArgs(value: String): InvokeArgs
}