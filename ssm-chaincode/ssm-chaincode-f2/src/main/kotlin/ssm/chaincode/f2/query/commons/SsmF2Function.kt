package ssm.chaincode.f2.query.commons

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.f2Function
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.sdk.client.SsmClient
import ssm.sdk.client.SsmClientConfig

fun <T, R> ssmF2Function(
	config: SsmChaincodeConfig, fnc: suspend (T, SsmClient) -> R
): F2Function<T, R> = f2Function { cmd ->
	SsmClientConfig(config.url)
		.let(SsmClient::fromConfig)
		.let { ssmClient ->
			fnc(cmd, ssmClient)
		}

}
