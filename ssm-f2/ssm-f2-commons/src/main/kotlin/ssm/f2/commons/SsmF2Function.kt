package ssm.f2.commons

import f2.dsl.function.F2Function
import f2.function.spring.adapter.f2Function
import ssm.client.SsmClient
import ssm.client.SsmClientConfig
import ssm.dsl.SsmCommand

fun <T: SsmCommand, R> ssmF2Function(fnc: suspend (T, SsmClient) -> R): F2Function<T, R> = f2Function { cmd ->
    val config = SsmClientConfig(cmd.baseUrl, cmd.channelId, cmd.chaincodeId, cmd.bearerToken)
    val ssmClient = SsmClient.fromConfig(config)
    fnc(cmd, ssmClient)
}