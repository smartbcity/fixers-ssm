package ssm.chaincode.f2.commons

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.f2Function
import ssm.chaincode.client.SsmClient
import ssm.chaincode.client.SsmClientConfig
import ssm.chaincode.dsl.SsmCommandDTO

fun <T: SsmCommandDTO, R> ssmF2Function(fnc: suspend (T, SsmClient) -> R): F2Function<T, R> = f2Function { cmd ->
    val config = SsmClientConfig(cmd.baseUrl, cmd.channelId, cmd.chaincodeId, cmd.bearerToken)
    val ssmClient = SsmClient.fromConfig(config)
    fnc(cmd, ssmClient)
}