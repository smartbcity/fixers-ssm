package ssm.sync.sdk.cucumber

import f2.dsl.fnc.invokeWith
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import ssm.bdd.config.SsmQueryStep
import ssm.bdd.config.contextualize
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.SsmSessionStateLog
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.chaincode.dsl.model.uri.burstChaincode
import ssm.couchdb.dsl.model.DocTypeName
import ssm.couchdb.dsl.query.CouchdbAdminListQuery
import ssm.couchdb.dsl.query.CouchdbSsmListQuery
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateGetQuery
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQuery
import ssm.couchdb.dsl.query.CouchdbUserListQuery
import ssm.couchdb.f2.CouchdbSsmQueriesFunctionImpl
import ssm.data.bdd.TestConfig
import ssm.sync.sdk.SsmSyncF2Builder
import ssm.sync.sdk.SyncSsmCommand
import ssm.sync.sdk.SyncSsmCommandResult

class CouchdbSsmSteps : SsmQueryStep(), En {

	companion object {
		const val GLUE = "ssm.sync.sdk"
	}


	var lastChanges: SyncSsmCommandResult? = null

	val couchdbSsmQueriesFunctions = CouchdbSsmQueriesFunctionImpl(
		TestConfig.local.couchdb
	)

	private val ssmSyncF2 = SsmSyncF2Builder.build(TestConfig.local)

	init {
		prepareSteps()

		When("I get changed for session {string} for {string}") { sessionName: SessionName, ssmName: SsmName ->
			runBlocking {
				val lastChanges =
					getChanges(bag.chaincodeUri, ssmName.contextualize(bag), sessionName.contextualize(bag))
				println(lastChanges)
			}
		}
		Then("Changes for session {string} for {string} is") { sessionName: SessionName, ssmName: SsmName, dataTable: DataTable ->
			runBlocking {
				lastChanges = getChanges(bag.chaincodeUri, ssmName.contextualize(bag), sessionName.contextualize(bag))
				Assertions.assertThat(dataTable.asCucumberChanges().size).isEqualTo(lastChanges!!.items.size)
			}
		}
		Then("Changes for session {string} for {string} is empty") { sessionName: SessionName, ssmName: SsmName ->
			runBlocking {
				lastChanges = getChanges(bag.chaincodeUri, ssmName.contextualize(bag), sessionName.contextualize(bag))
				Assertions.assertThat(lastChanges?.items ?: emptyList()).isEmpty()

			}
		}
	}

	fun DataTable.asCucumberChanges(): List<CucumberChanges> {
		return asMaps().map { columns ->
			CucumberChanges(
				objectId = columns[CucumberChanges::objectId.name]!!,
				docType = columns[CucumberChanges::docType.name]!!,
			)
		}
	}

	suspend fun getChanges(
		chaincodeUri: ChaincodeUri,
		ssmName: SsmName,
		sessionName: SessionName
	): SyncSsmCommandResult {
		return SyncSsmCommand(
			lastEventId = lastChanges?.lastEventId,
			chaincodeUri = chaincodeUri,
			ssmName = ssmName,
			sessionName = sessionName
		).invokeWith(ssmSyncF2)
	}

	override suspend fun getSession(ssmName: SsmName, sessionName: SessionName): SsmSessionStateDTO {
		return CouchdbSsmSessionStateGetQuery(
			chaincodeUri = bag.chaincodeUri,
			sessionName = sessionName,
			ssmName = ssmName
		).invokeWith(
			couchdbSsmQueriesFunctions.couchdbSsmSessionStateGetQueryFunction()
		).item
	}

	override suspend fun logSession(sessionName: String): List<SsmSessionStateLog> {
		return bag.clientQuery.log(sessionName)
	}

	override suspend fun listSessions(): List<SessionName> {
		return CouchdbSsmSessionStateListQuery(chaincodeUri = bag.chaincodeUri)
			.invokeWith(couchdbSsmQueriesFunctions.couchdbSsmSessionStateListQueryFunction())
			.page.list
			.map { it.session }
	}

	override suspend fun listSsm(): List<SsmName> {
		val uri = bag.chaincodeUri.burstChaincode()!!
		return CouchdbSsmListQuery(
			pagination = null,
			channelId = uri.channelId,
			chaincodeId = uri.chaincodeId
		).invokeWith(couchdbSsmQueriesFunctions.couchdbSsmListQueryFunction())
			.page.list.map { it.name }
	}

	override suspend fun listUsers(): List<String> {
		return CouchdbUserListQuery(bag.chaincodeUri).invokeWith(
			couchdbSsmQueriesFunctions.couchdbUserListQueryFunction()
		).items.map { it.name }
	}

	override suspend fun listAdmins(): List<String> {
		return CouchdbAdminListQuery(bag.chaincodeUri)
			.invokeWith(
				couchdbSsmQueriesFunctions.couchdbAdminListQueryFunction()
			).items.map { it.name }
	}
}


class CucumberChanges(
	val objectId: String,
	val docType: DocTypeName,
)
