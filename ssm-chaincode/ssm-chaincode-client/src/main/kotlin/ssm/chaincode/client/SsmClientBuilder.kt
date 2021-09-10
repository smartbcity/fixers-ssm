package ssm.chaincode.client

import java.io.IOException
import ssm.chaincode.client.repository.CoopRepository
import ssm.chaincode.client.repository.RepositoryFactory
import ssm.sdk.json.JSONConverter
import ssm.sdk.json.JSONConverterObjectMapper

class SsmClientBuilder(
	private var coopRepository: CoopRepository? = null,
	private var jsonConverter: JSONConverter? = null,
	private var channelId: String? = null,
	private var ssmId: String? = null,
) {
	fun withCoopRepository(coopRepository: CoopRepository?): SsmClientBuilder {
		this.coopRepository = coopRepository
		return this
	}

	fun withJSONConverter(jsonConverter: JSONConverter?): SsmClientBuilder {
		this.jsonConverter = jsonConverter
		return this
	}

	fun withChannelId(channelId: String?): SsmClientBuilder {
		this.channelId = channelId
		return this
	}

	fun withSsmId(ssmId: String?): SsmClientBuilder {
		this.ssmId = ssmId
		return this
	}

	fun build(): SsmClient {
		return SsmClient(SsmRequester(channelId, ssmId, jsonConverter!!, coopRepository!!))
	}

	companion object {
		fun builder(): SsmClientBuilder {
			return SsmClientBuilder()
		}

		@Throws(IOException::class)
		fun builder(filename: String): SsmClientBuilder {
			val config = SsmClientConfig.fromConfigFile(filename)
			return builder(config)
		}

		fun builder(config: SsmClientConfig): SsmClientBuilder {
			val factory = RepositoryFactory(config.baseUrl, config.bearerToken)
			val coopRepository = factory.buildCoopRepository()
			val converter: JSONConverter = JSONConverterObjectMapper()
			return builder()
				.withCoopRepository(coopRepository)
				.withJSONConverter(converter)
				.withChannelId(config.channelId)
				.withSsmId(config.chaincodeId)
		}
	}
}
