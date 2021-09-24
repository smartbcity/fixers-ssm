package features

import f2.dsl.fnc.invoke
import io.cucumber.java8.En
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import ssm.chaincode.dsl.Chaincode
import ssm.chaincode.dsl.Ssm
import ssm.couchdb.dsl.config.CouchdbConfig
import ssm.couchdb.dsl.model.DatabaseDTO
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQuery
import ssm.couchdb.dsl.query.CouchdbDatabaseListQuery
import ssm.couchdb.dsl.query.CouchdbSsmListQuery
import ssm.couchdb.f2.CouchDbSsmQueriesImpl
import ssm.couchdb.f2.query.CouchdbSsmListQueryFunctionImpl
import ssm.couchdb.f2.query.TestConfig

class DatabasesSteps : En {

	private lateinit var config: CouchdbConfig
	private var databases: List<DatabaseDTO> = emptyList()
	private var ssms: List<Ssm> = emptyList()

	init {
		DataTableType { entry: Map<String, String> ->
			Chaincode(
				id = entry["chaincodeId"] ?: "",
				channelId = entry["channelId"] ?: ""
			)
		}

		Given("I have a local database") {
			config = TestConfig.dbConfig
		}

		When("I get the list of databases") {
			runBlocking {
				databases = CouchDbSsmQueriesImpl().couchdbDatabaseListQueryFunction(config).invoke(
					CouchdbDatabaseListQuery()
				).page.list
			}
		}

		When("I get the list of databases filtered by {string} with {string}") { filter: String, value: String ->
			runBlocking {
				databases = CouchDbSsmQueriesImpl().couchdbDatabaseListQueryFunction(config).invoke(
					when (filter) {
						CouchdbDatabaseListQuery::channelId.name -> CouchdbDatabaseListQuery(
							channelId = value,
							chaincodeId = null
						)
						CouchdbDatabaseListQuery::chaincodeId.name -> CouchdbDatabaseListQuery(
							channelId = null,
							chaincodeId = value
						)
						else -> CouchdbDatabaseListQuery(null, null)
					}
				).page.list
			}
		}

		When("I get the database for") { chaincode: Chaincode ->
			runBlocking {
				databases = CouchDbSsmQueriesImpl().couchDbDatabaseGetQueryFunction(config).invoke(
					CouchdbDatabaseGetQuery(
						channelId = chaincode.channelId,
						chaincodeId = chaincode.id,
					)
				).item.let {
					listOfNotNull(it)
				}
			}
		}

		Then<String>("I found the database {string}") { name ->
			Assertions.assertThat(databases.map { it.name }).contains(name)
		}

		Then("I do not found database") {
			Assertions.assertThat(databases).isEmpty()
		}

		When("I get all ssm for") { chaincode: Chaincode ->
			runBlocking {
				ssms = CouchdbSsmListQueryFunctionImpl(config)
					.couchdbSsmListQueryFunction()
					.invoke(CouchdbSsmListQuery(
						channelId = chaincode.channelId,
						chaincodeId = chaincode.id,
						pagination = null
					)).page.list
			}
		}
		Then("I do not found ssm") {
			Assertions.assertThat(ssms).isEmpty()
		}
		Then("I found ssm") {
			Assertions.assertThat(ssms).isNotEmpty
		}
	}
}
