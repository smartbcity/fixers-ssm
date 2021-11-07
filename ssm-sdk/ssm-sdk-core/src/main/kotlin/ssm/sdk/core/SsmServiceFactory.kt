package ssm.sdk.core

import java.io.IOException
import ssm.sdk.client.SsmRequester
import ssm.sdk.client.SsmSdkConfig
import ssm.sdk.client.ktor.KtorRepository
import ssm.sdk.core.signer.SsmCmdSignerSha256RSASigner
import ssm.sdk.json.JSONConverter
import ssm.sdk.json.JSONConverterObjectMapper

class SsmServiceFactory(
	private var coopRepository: KtorRepository ? = null,
	private var jsonConverter: JSONConverter? = null,
	private var channelId: String? = null,
	private var ssmId: String? = null,
) {
	fun withCoopRepository(coopRepository: KtorRepository?): SsmServiceFactory {
		this.coopRepository = coopRepository
		return this
	}

	fun withJSONConverter(jsonConverter: JSONConverter?): SsmServiceFactory {
		this.jsonConverter = jsonConverter
		return this
	}

	fun withChannelId(channelId: String?): SsmServiceFactory {
		this.channelId = channelId
		return this
	}

	fun withSsmId(ssmId: String?): SsmServiceFactory {
		this.ssmId = ssmId
		return this
	}

	fun buildQueryService(): SsmQueryService {
		return SsmQueryService(SsmRequester(channelId, ssmId, jsonConverter!!, coopRepository!!))
	}

	fun buildTxService(): SsmTxService {
		return SsmTxService(
			SsmRequester(channelId, ssmId, jsonConverter!!, coopRepository!!),
			SsmCmdSignerSha256RSASigner()
		)
	}

	companion object {
//		fun builder(): SsmServiceFactory {
//			return SsmServiceFactory()
//		}
//
		@Throws(IOException::class)
		fun builder(filename: String): SsmServiceFactory {
			val config = SsmSdkConfig.fromConfigFile(filename)
			return builder(config)
		}

		fun builder(config: SsmSdkConfig): SsmServiceFactory {
			val coopRepository = KtorRepository(config.baseUrl, "config.bearerToken")
			val converter: JSONConverter = JSONConverterObjectMapper()
			return SsmServiceFactory()
				.withCoopRepository(coopRepository)
				.withJSONConverter(converter)
		}
	}
}
