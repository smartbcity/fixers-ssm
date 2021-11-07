package ssm.chaincode.f2.utils

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.f2Function
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.sdk.client.SsmSdkConfig
import ssm.sdk.core.SsmServiceFactory
import ssm.sdk.core.SsmTxService

fun <T, R> ssmF2Function(
	config: SsmChaincodeConfig, fnc: suspend (T, SsmTxService) -> R
): F2Function<T, R> = f2Function { cmd ->
	SsmServiceFactory
		.builder(
			SsmSdkConfig(config.url)
		)
		.buildTxService()
		.let { ssmClient ->
			fnc(cmd, ssmClient)
		}

}
