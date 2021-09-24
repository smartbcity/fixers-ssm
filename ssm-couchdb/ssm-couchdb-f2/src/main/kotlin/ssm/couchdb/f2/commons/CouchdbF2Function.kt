package ssm.couchdb.f2.commons

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.f2Function
import ssm.chaincode.dsl.ChaincodeId
import ssm.chaincode.dsl.ChannelId
import ssm.couchdb.client.SsmCouchDbClient
import ssm.couchdb.client.builder.SsmCouchDbBasicAuth
import ssm.couchdb.dsl.config.CouchdbConfig

object CouchdbF2Function {

	fun <T , R> function(config: CouchdbConfig, fnc: suspend (T, SsmCouchDbClient) -> R): F2Function<T, R> = f2Function { cmd ->
		val couchdbClient = couchdbClient(config)
		fnc(cmd, couchdbClient)
	}

	private fun couchdbClient(config: CouchdbConfig): SsmCouchDbClient {
		return SsmCouchDbClient.builder()
			.withUrl(config.url)
			.withName(config.serviceName)
			.withAuth(
				SsmCouchDbBasicAuth(
					username = config.username,
					password = config.password,
				)
			).build()
	}
}

fun chainCodeDbName(channelId: ChannelId, chaincodeId: ChaincodeId) = "${channelId}_$chaincodeId"
