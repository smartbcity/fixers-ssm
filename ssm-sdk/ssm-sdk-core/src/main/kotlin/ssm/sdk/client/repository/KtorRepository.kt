package ssm.sdk.client.repository

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.request.header
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.parameter
import io.ktor.http.contentType
import io.ktor.http.ContentType


class KtorRepository(
	private val baseUrl: String, val bearerToken: String?
) {

	val client = HttpClient(CIO) {
		install(JsonFeature) {
			serializer = JacksonSerializer()
		}
		defaultRequest {
			header("Authorization", bearerToken)
		}
	}

	suspend fun invokeCommand(
		cmd: String,
		channelId: String?,
		chaincodeId: String?,
		fcn: String,
		args: List<String>,
	): String {
		return client.get(baseUrl) {
			parameter("cmd", cmd)
			channelId?.let { parameter("channelId", channelId) }
			chaincodeId?.let { parameter("chaincodeId", chaincodeId) }
			parameter("fcn", fcn)
			parameter("args", args.first())
		}
	}

	suspend fun getBlock(blockId: Long, channelId: String?): String {
		return client.get(baseUrl) {
			channelId?.let { parameter("channelId", channelId) }
			url {
				path("blocks", blockId.toString())
			}
		}
	}

	suspend fun getTransaction(txId: String, channelId: String?): String {
		return client.get(baseUrl) {
			channelId?.let { parameter("channelId", channelId) }
			url {
				path("transactions", txId)
			}
		}
	}

	suspend fun invokeCommand(invokeArgs: CommandArgs): String {
		return client.post(baseUrl) {
			contentType(ContentType.Application.Json)
			body = invokeArgs
		}
	}
}
