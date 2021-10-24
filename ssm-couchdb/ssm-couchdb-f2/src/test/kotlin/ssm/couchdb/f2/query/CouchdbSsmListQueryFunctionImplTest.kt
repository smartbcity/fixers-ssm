package ssm.couchdb.f2.query

import f2.dsl.fnc.invoke
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.bdd.couchdb.TestConfig
import ssm.couchdb.dsl.query.CouchdbSsmListQuery
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction

internal class CouchdbSsmListQueryFunctionImplTest : FunctionTestBase() {

	var couchdbSsmListQueryFunctionImpl: CouchdbSsmListQueryFunction = queries.couchdbSsmListQueryFunction(
		config = TestConfig.dbConfig,
	)


	@Test
	fun `must return all ssm`(): Unit = runBlocking {
		val command = CouchdbSsmListQuery(
			pagination = null,
			channelId = TestConfig.CHANNEL_ID,
			chaincodeId = TestConfig.CHAINCODE_ID
		)
		val ssmList = couchdbSsmListQueryFunctionImpl(command)

		Assertions.assertThat(ssmList).isNotNull
		Assertions.assertThat(ssmList.page.list).isNotNull
	}
}
