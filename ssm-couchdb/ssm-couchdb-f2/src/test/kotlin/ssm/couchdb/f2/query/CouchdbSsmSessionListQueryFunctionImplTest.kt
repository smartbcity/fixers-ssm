package ssm.couchdb.f2.query

import f2.dsl.fnc.invoke
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.bdd.couchdb.TestConfig
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.chaincode.dsl.model.uri.ChaincodeUriBurst
import ssm.chaincode.dsl.model.uri.SsmUriBurst
import ssm.chaincode.dsl.model.uri.compact
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQuery
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction

internal class CouchdbSsmSessionListQueryFunctionImplTest : FunctionTestBase() {

	var couchdbSsmSessionListQueryFunction: CouchdbSsmSessionStateListQueryFunction
		= queries.couchdbSsmSessionStateListQueryFunction(TestConfig.dbConfig)

	@Test
	fun `must return all sessions`(): Unit = runBlocking {
		val command = CouchdbSsmSessionStateListQuery(
			chaincodeUri = ChaincodeUriBurst(
				channelId = TestConfig.CHANNEL_ID,
				chaincodeId = TestConfig.CHAINCODE_ID,
			).compact(),
			ssm = null,
			pagination = null
		)
		val sessions = couchdbSsmSessionListQueryFunction(command).page.list
		Assertions.assertThat(sessions).isNotNull
	}
}
