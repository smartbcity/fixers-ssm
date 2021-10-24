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

		Given("An admin {string}") { adminName: SignerName ->
			bag.adminSigner = loadSignerAdmin(adminName)
		}

		Given("The admin {string} with key {string}") { adminName: SignerName, key: String ->
			bag.adminSigner = loadSignerAdmin(adminName, key)
		}

		Given("The user {string} with key {string}") { userName: SignerName, key: String ->
			bag.userSigners[userName] = loadSigner(userName, key)
		}

		Given("A new user {string}") { userName: SignerName ->
			bag.userSigners[userName] = Signer.generate(userName)
			runBlocking {
				registerUser(bag.userSigners[userName]!!.asAgent())
			}
		}

		Given("A ssm {string} with configuration file {string}") { ssmName: SsmName, ssmConfigFile: String ->
			bag.ssms[ssmName] = SsmJsonReader().readSsm(ssmConfigFile, ssmName)
			runBlocking {
				createSsm(bag.ssms[ssmName]!!)
			}
		}

		Given("A ssm {string} with transitions") { ssmName: SsmName, transitions: List<SsmTransition> ->
			bag.ssms[ssmName] = Ssm(
				transitions = transitions,
				name = ssmName
			)
			runBlocking {
				createSsm(bag.ssms[ssmName]!!)
			}
		}

		Given("A session {string} started for {string}") { sessionName: SessionName, ssmName: SsmName ->
			runBlocking {
				val session = SsmSession(
					ssm = ssmName,
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
				registerUser(bag.userSigners[userName]!!.asAgent())
			}
		}
		When("I create a ssm {string} with configuration file {string}") { ssmName: SsmName, ssmConfigFile: String ->
			bag.ssms[ssmName] = SsmJsonReader().readSsm(ssmConfigFile, ssmName)
			runBlocking {

				createSsm(bag.ssms[ssmName]!!)
			}
		}
		When("I create a ssm {string} with transitions") { ssmName: SsmName, transitions: List<SsmTransition> ->
			bag.ssms[ssmName] = Ssm(
				transitions = transitions,
				name = ssmName
			)
			runBlocking {
				createSsm(bag.ssms[ssmName]!!)
			}
		}
		When("I start a session {string} for {string}") { sessionName: SessionName, ssmName: SsmName ->
			runBlocking {
				val session = SsmSession(
					ssm = ssmName,
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
				val signer = bag.userSigners[userName]!!
				val context = SsmContext(
					session = sessionName.contextualize(bag),
					public = "public",
					iteration = iteration,

					)
				bag.client.perform(signer, actionName, context)
			}
		}
	}

	protected abstract suspend fun startSession(session: SsmSession)

	protected abstract suspend fun createSsm(ssm: Ssm)

	protected abstract suspend fun registerUser(ssmAgent: SsmAgent)

	protected abstract fun loadSignerAdmin(adminName: SignerName, filename: String? = null): SignerAdmin

	protected abstract fun loadSigner(userName: SignerName, filename: String): Signer
}
