import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.SsmSessionStateLog

abstract class SsmQueryStep {

	lateinit var bag: SsmCucumberBag
	lateinit var config: SsmChaincodeConfig

	@Suppress("LongMethod")
	fun En.prepareSteps() {
		Before { scenario: Scenario ->
			bag = SsmCucumberBag.init(scenario)
			config = SsmChaincodeConfig(
				bag.config.baseUrl
			)
		}

		When("I get the list of admin") {
			bag.adminsName = runBlocking {
				listAdmins()
			}
		}

		When("I get the list of user") {
			runBlocking {
				bag.usersName = listUsers()
			}
		}

		When("I get the list of ssm") {
			runBlocking {
				bag.ssmsName = listSsm()
			}
		}
		When("I get the list of session") {
			runBlocking {
				bag.sessions = listSessions()
			}
		}
		Then("Admin {string} is in the list") { adminName: String ->
			runBlocking {
				Assertions.assertThat(
					listAdmins()
				).contains(adminName)
			}
		}
		Then("User {string} is in the list") { userName: String ->
			runBlocking {
				Assertions.assertThat(
					listUsers()
				).contains(userName)
			}
		}
		Then("Ssm {string} is in the list") { ssmName: SsmName ->
			runBlocking {
				Assertions.assertThat(
					listSsm()
				).contains(ssmName)
			}
		}
		Then("Session {string} is in the list") { sessionName: SessionName ->
			runBlocking {
				Assertions.assertThat(
					listSessions()
				).contains(sessionName)
			}
		}
		Then("Session {string} have current state origin {string} current {string} iteration {string}")
		{ sessionName: String, origin: String, current: String, iteration: String ->
			runBlocking {
				Assertions.assertThat(getSession(sessionName)).hasFieldOrProperty(SsmSessionState::origin.name)
					.isEqualTo(origin)
					.hasFieldOrProperty(SsmSessionState::current.name).isEqualTo(current)
					.hasFieldOrProperty(SsmSessionState::iteration.name).isEqualTo(iteration)
			}
		}

		Then("Admin {string} is not in the list") { adminName: String ->
			runBlocking {
				Assertions.assertThat(
					listAdmins()
				).doesNotContain(adminName)
			}
		}
		Then("User {string} is not in the list") { userName: String ->
			runBlocking {
				Assertions.assertThat(
					listUsers()
				).doesNotContain(userName)
			}
		}
		Then("Ssm {string} is not in the list") { ssmName: SsmName ->
			runBlocking {
				Assertions.assertThat(
					listSsm()
				).doesNotContain(ssmName)
			}
		}

		Then("The action {string} has been performed for session {string}")
		{ actionName: String, sessionName: SessionName ->
			runBlocking {
				Assertions.assertThat(
					logSession(sessionName).mapNotNull { it.state.origin }.map { it.action }
				).contains(actionName)
			}
		}

		Then("The session {string} have log with {string} entries")
		{ sessionName: SessionName, nbEntries: String ->
			runBlocking {
				Assertions.assertThat(
					logSession(sessionName)
				).hasSize(nbEntries.toInt())
			}
		}

		Then("The session {string} have logs") { _: SessionName ->
			runBlocking {
				TODO("Not yet implemented")
			}
		}
	}

	protected abstract suspend fun getSession(sessionName: SessionName): SsmSessionStateDTO?

	protected abstract suspend fun logSession(sessionName: SessionName): List<SsmSessionStateLog>

	protected abstract suspend fun listSessions(): List<SessionName>

	protected abstract suspend fun listSsm(): List<SsmName>

	protected abstract suspend fun listUsers(): List<String>

	protected abstract suspend fun listAdmins(): List<String>
}
