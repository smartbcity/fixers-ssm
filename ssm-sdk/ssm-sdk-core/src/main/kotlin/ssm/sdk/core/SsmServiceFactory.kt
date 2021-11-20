package ssm.sdk.core

import java.io.IOException
import ssm.sdk.core.auth.BearerTokenAuthCredentials
import ssm.sdk.core.ktor.KtorRepository
import ssm.sdk.core.ktor.SsmRequester
import ssm.sdk.json.JSONConverter
import ssm.sdk.json.JSONConverterObjectMapper
import ssm.sdk.sign.SsmCmdSigner

class SsmServiceFactory(
	private var coopRepository: KtorRepository,
	private var jsonConverter: JSONConverter,
	private var channelId: String? = null,
	private var ssmId: String? = null,
) {

	fun withChannelId(channelId: String?): SsmServiceFactory {
		this.channelId = channelId
		return this
	}

	fun withSsmId(ssmId: String?): SsmServiceFactory {
		this.ssmId = ssmId
		return this
	}

	fun buildQueryService(): SsmQueryService {
		return SsmQueryService(SsmRequester(channelId, ssmId, jsonConverter, coopRepository))
	}

	fun buildTxService(ssmCmdSigner: SsmCmdSigner): SsmTxService {
		return SsmTxService(
			SsmRequester(channelId, ssmId, jsonConverter, coopRepository),
			ssmCmdSigner
		)
	}

	companion object {
		@Throws(IOException::class)
		fun builder(filename: String, bearerTokenHeaderProvider: BearerTokenAuthCredentials? = null): SsmServiceFactory {
			val config = SsmSdkConfig.fromConfigFile(filename)
			return builder(config, bearerTokenHeaderProvider)
		}

		fun builder(config: SsmSdkConfig, bearerTokenHeaderProvider: BearerTokenAuthCredentials? = null): SsmServiceFactory {
			val coopRepository = KtorRepository(config.baseUrl, bearerTokenHeaderProvider)
			val converter: JSONConverter = JSONConverterObjectMapper()
			return SsmServiceFactory(
				coopRepository = coopRepository,
				jsonConverter = converter,
			)
		}
	}
}
