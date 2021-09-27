package ssm.couchdb.f2.commons

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.f2Function
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.couchdb.client.SsmCouchDbClient
import ssm.couchdb.client.builder.SsmCouchDbBasicAuth
import ssm.couchdb.dsl.config.SsmCouchdbConfig

object CouchdbF2Function {

	fun <T , R> function(config: SsmCouchdbConfig, fnc: suspend (T, SsmCouchDbClient) -> R): F2Function<T, R> = f2Function { cmd ->
		val couchdbClient = couchdbClient(config)
		fnc(cmd, couchdbClient)
	}

	private fun couchdbClient(config: SsmCouchdbConfig): SsmCouchDbClient {
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
