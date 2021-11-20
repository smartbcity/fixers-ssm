package ssm.sdk.core.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.sdk.core.auth.AuthCredentials
import ssm.sdk.core.auth.BearerTokenAuthCredentials


class KtorRepository(
	private val baseUrl: String,
	private val authCredentials: AuthCredentials?,
) {
	companion object {
		const val CMD_PROPS = "cmd"
		const val CHANNEL_ID_PROPS = "channelId"
		const val CHAINCODE_ID_PROPS = "chaincodeId"
		const val FCN_PROPS = "fcn"
		const val ARGS_PROPS = "args"
	}

	val client = HttpClient(CIO) {
		install(JsonFeature) {
			serializer = JacksonSerializer()
		}

	}

	suspend fun query(
		cmd: String,
		fcn: String,
		args: List<String>,
		channelId: ChannelId?,
		chaincodeId: ChaincodeId?,
	): String {
		return client.get(baseUrl) {
			addAuth()

			parameter(CMD_PROPS, cmd)
			channelId?.let { parameter(CHANNEL_ID_PROPS, channelId) }
			chaincodeId?.let { parameter(CHAINCODE_ID_PROPS, chaincodeId) }
			parameter(FCN_PROPS, fcn)
			parameter(ARGS_PROPS, args.first())
		}
	}

	suspend fun getBlock(blockId: Long, channelId: ChannelId?): String {
		return client.get(baseUrl) {
			addAuth()

			channelId?.let { parameter("channelId", channelId) }
			url {
				path("blocks", blockId.toString())
			}
		}
	}

	suspend fun getTransaction(txId: String, channelId: ChannelId?): String {
		return client.get(baseUrl) {
			addAuth()

			channelId?.let { parameter("channelId", channelId) }
			url {
				path("transactions", txId)
			}
		}
	}

	suspend fun invoke(
		cmd: String,
		fcn: String,
		args: List<String>,
		channelId: ChannelId?,
		chaincodeId: ChaincodeId?,
	): String {
		return client.post(baseUrl) {
			addAuth()

			contentType(ContentType.Application.Json)
			body = mapOf(
				CMD_PROPS to cmd,
				FCN_PROPS to fcn,
				ARGS_PROPS to args,
				CHANNEL_ID_PROPS to channelId,
				CHAINCODE_ID_PROPS to chaincodeId,
			)
		}
	}

	private fun HttpRequestBuilder.addAuth() {
		when(authCredentials) {
			is BearerTokenAuthCredentials -> header("Authorization", "Bearer ${authCredentials.getBearerToken()}")
		}
	}

}


