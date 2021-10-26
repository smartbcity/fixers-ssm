package ssm.bdd.config

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import kotlinx.coroutines.runBlocking
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAction
import ssm.chaincode.dsl.model.SsmAgent
import ssm.chaincode.dsl.model.SsmContext
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSession
import ssm.chaincode.dsl.model.SsmTransition
import ssm.sdk.client.extention.asAgent
import ssm.sdk.client.utils.SsmJsonReader
import ssm.sdk.sign.model.Signer
import ssm.sdk.sign.model.SignerAdmin
import ssm.sdk.sign.model.SignerName

abstract class SsmCommandStep {

	lateinit var bag: SsmCucumberBag
	lateinit var config: SsmChaincodeConfig

	@Suppress("LongMethod")
	fun En.prepareSteps() {
		DataTableType { table: DataTable ->
			val rows: List<Map<String?, String?>> = table.asMaps()
			rows.map { columns ->
				SsmTransition(
					from = columns[SsmTransition::from.name]?.toInt()!!,
					to = columns[SsmTransition::to.name]?.toInt()!!,
					role = columns[SsmTransition::role.name]!!,
					action = columns[SsmTransition::action.name]!!,

					)
			}
		}

		Before { scenario: Scenario ->
			bag = SsmCucumberBag.init(scenario)
		}

		Given("An admin") {
			runBlocking {
				bag.adminSigner = loadSignerAdmin()
			}
		}

		Given("The admin {string}") { adminName: SignerName ->

			runBlocking {
				bag.adminSigner = loadSignerAdmin(adminName)
			}
		}

		Given("The admin {string} with key {string}") { adminName: SignerName, key: String ->
			runBlocking {
				bag.adminSigner = loadSignerAdmin(adminName, key)
			}
		}

		Given("The user {string} with key {string}") { userName: SignerName, key: String ->
			runBlocking {
				bag.userSigners[userName] = loadSigner(userName, key)
			}
		}

		Given("A new user {string}") { userName: SignerName ->
			val contextUser = userName.contextualize(bag)
			bag.userSigners[contextUser] = Signer.generate(contextUser)
			runBlocking {
				registerUser(bag.userSigners[contextUser]!!.asAgent())
			}
		}

		Given("A ssm {string} with configuration file {string}") { ssmName: SsmName, ssmConfigFile: String ->
			val contextSsm = ssmName.contextualize(bag)
			bag.ssms[contextSsm] = SsmJsonReader().readSsm(ssmConfigFile, contextSsm)
			runBlocking {
				createSsm(bag.ssms[contextSsm]!!)
			}
		}

		Given("A ssm {string} with transitions") { ssmName: SsmName, table: DataTable ->
			val contextSsm = ssmName.contextualize(bag)
			bag.ssms[contextSsm] = Ssm(
				transitions = table.asTransitions(),
				name = contextSsm
			)
			runBlocking {
				createSsm(bag.ssms[contextSsm]!!)
			}
		}

		Given("A session {string} started for {string}") { sessionName: SessionName, ssmName: SsmName ->
			val contextSsm = ssmName.contextualize(bag)
			runBlocking {
				val session = SsmSession(
					ssm = contextSsm,
					session = sessionName.contextualize(bag),
					roles = emptyMap(),
					public = "",
					private = emptyMap()
				)
				startSession(session)
			}
		}
		When("I register a user {string}") { userName: String ->
			runBlocking {
				val contextUser = userName.contextualize(bag)
				if(!bag.userSigners.containsKey(contextUser)){
					bag.userSigners[contextUser] = createUser(contextUser)
				}
				val user = bag.userSigners[contextUser]!!.asAgent()
				registerUser(user)
			}
		}
		When("I create a ssm {string} with configuration file {string}") { ssmName: SsmName, ssmConfigFile: String ->
			val contextSsm = ssmName.contextualize(bag)
			bag.ssms[contextSsm] = SsmJsonReader().readSsm(ssmConfigFile, contextSsm)
			runBlocking {

				createSsm(bag.ssms[contextSsm]!!)
			}
		}
		When("I create a ssm {string} with transitions") { ssmName: SsmName, table: DataTable ->
			val contextSsm = ssmName.contextualize(bag)
			bag.ssms[contextSsm] = Ssm(
				transitions = table.asTransitions(),
				name = contextSsm
			)
			runBlocking {
				createSsm(bag.ssms[contextSsm]!!)
			}
		}
		When("I start a session {string} for {string}") { sessionName: SessionName, ssmName: SsmName ->
			runBlocking {
				val contextSsm = ssmName.contextualize(bag)
				val session = SsmSession(
					ssm = contextSsm,
					session = sessionName.contextualize(bag),
					roles = emptyMap(),
					public = "",
					private = emptyMap()
				)
				startSession(session)
			}
		}
		When("I perform action {string}[{int}] for {string} with {string}")
		{ actionName: SsmAction, iteration: Int, sessionName: SessionName, userName: String ->
			runBlocking {
				val contextUser = userName.contextualize(bag)
				val signer = bag.userSigners[contextUser]!!
				val context = SsmContext(
					session = sessionName.contextualize(bag),
					public = "public",
					iteration = iteration,

					)
				bag.client.perform(signer, actionName, context)
			}
		}
	}

	fun DataTable.asTransitions(): List<SsmTransition> {
		return asMaps().map { columns ->
			SsmTransition(
				from = columns[SsmTransition::from.name]?.toInt()!!,
				to = columns[SsmTransition::to.name]?.toInt()!!,
				role = columns[SsmTransition::role.name]!!,
				action = columns[SsmTransition::action.name]!!,

				)
		}
	}


	protected fun createUser(signerName: SignerName): Signer {
		return Signer.generate(signerName)
	}

	protected abstract suspend fun startSession(session: SsmSession)

	protected abstract suspend fun createSsm(ssm: Ssm)

	protected abstract suspend fun registerUser(ssmAgent: SsmAgent)

	protected suspend fun loadSignerAdmin(adminName: SignerName? = null, filename: String? = null): SignerAdmin {
		return when {
			adminName != null -> {
				SignerAdmin.loadFromFile("ssm-admin", filename)
			}
			System.getProperty("profile") == "local" -> {
				SignerAdmin.loadFromFile("ssm-admin", "local/admin/ssm-admin")
			}
			else -> {
				SignerAdmin.loadFromFile("ssm-admin", "local/admin/ssm-admin")
			}
		}

	}

	protected abstract suspend fun loadSigner(userName: SignerName, filename: String): Signer
}
