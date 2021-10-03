package ssm.chaincode.client.features

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import java.util.UUID
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.assertj.core.util.Lists
import ssm.chaincode.client.SsmClientItTest
import ssm.chaincode.client.SsmClientTestBuilder
import ssm.chaincode.client.extention.loadFromFile
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgentName
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSession
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmTransition
import ssm.sdk.sign.model.Signer

class SsmsSteps : En {
	companion object {
		private const val NETWORK = "bclan-it/"
	}

	lateinit var uuid: String

	val client = SsmClientTestBuilder.build()

	var admins: List<SsmAgentName> = emptyList()
	var agents: List<SsmAgentName> = emptyList()
	val transactions = mutableMapOf<String, InvokeReturn>()

	lateinit var adminSigner: Signer

	lateinit var ssmSchema: Ssm
	lateinit var ssmSession: SsmSession

	var ssms: List<SsmName> = emptyList()

	init {
		Before { scenario: Scenario ->
			uuid = UUID.randomUUID().toString()
		}

		When("I fetch the list of admin") {
			runBlocking {
				admins = client.listAdmins()
			}
		}
		Then("Admin with name {string} must be in the list") { agentName: SsmAgentName ->
			Assertions.assertThat(admins).contains(agentName)
		}

		Then("Agent with name {string} must be in the list") { agentName: SsmAgentName ->
			Assertions.assertThat(agents).contains(agentName.contextualize())
		}

		Then("Agent with name {string} must not be in the list") { agentName: SsmAgentName ->
			Assertions.assertThat(agents).doesNotContain(agentName.contextualize())
		}

		When("I fetch the list of user") {
			runBlocking {
				agents = client.listAgent()
			}
		}
		Given("An admin signer with name {string}") { agentName: SsmAgentName ->
			adminSigner = Signer.loadFromFile(SsmClientItTest.ADMIN_NAME, NETWORK + agentName)
		}
		When("I register a user with name {string}") { agentName: SsmAgentName ->
			runBlocking {
				val agent = loadFromFile(agentName.contextualize(), SsmClientItTest.USER1_FILENAME)
				val transactionEvent = client.registerUser(
					adminSigner,
					agent
				)!!
				transactions[agentName.contextualize()] = transactionEvent
			}
//			assertThatTransactionExists(trans)
		}
		Given("A ssm schema with name {string}") { ssmName: String ->
			val sell = SsmTransition(0, 1, "Seller", "Sell")
			val buy = SsmTransition(1, 2, "Buyer", "Buy")
			ssmSchema = Ssm(ssmName.contextualize(), Lists.newArrayList(sell, buy))
		}
		When("I create a ssm with name {string}") { ssmName: String ->
			runBlocking {
				transactions[ssmName.contextualize()] = client.create(adminSigner, ssmSchema)!!
			}
		}
		When("I fetch the list of ssm") {
			runBlocking {
				ssms = client.listSsm()
			}
		}
		Then("Ssm with name {string} must be in the list") { ssmName: SsmName ->
			Assertions.assertThat(ssms).contains(ssmName.contextualize())
		}
		Given("A ssm with name {string}") { ssmName: SsmName ->
			val sell = SsmTransition(0, 1, "Seller", "Sell")
			val buy = SsmTransition(1, 2, "Buyer", "Buy")
			val ssmSchema = Ssm(ssmName.contextualize(), Lists.newArrayList(sell, buy))
			runBlocking {
				transactions[ssmName.contextualize()] = client.create(adminSigner, ssmSchema)!!
			}
		}
		When("I start a session with {string} for {string}") { sessionName: SessionName, ssmName: SsmName ->
			val signerUser1 = loadFromFile(SsmClientItTest.USER1_NAME, SsmClientItTest.USER1_FILENAME)
			val agentUser2 = loadFromFile(SsmClientItTest.USER2_NAME, SsmClientItTest.USER2_FILENAME)
			val roles: Map<String, String> = mapOf(
				signerUser1.name to "Buyer", agentUser2.name to "Seller"
			)
			ssmSession = SsmSession(
				ssmName.contextualize(),
				sessionName.contextualize(), roles, "Used car for 100 dollars.", emptyMap()
			)
			runBlocking {
				transactions[sessionName.contextualize()] = client.start(adminSigner, ssmSession)!!
			}
		}
		Then("A transaction must has been done for {string}") { key: String ->
			Assertions.assertThat(transactions).containsKey(key.contextualize())
		}
		Then("details of session must be") { table: DataTable ->
			val props = table.asMaps<String, String>(String::class.java, String::class.java).first()
			val sessionName = props["sessionName"]!!.contextualize()
			runBlocking {
				val session = client.getSession(sessionName)
				Assertions.assertThat(session)
					.isNotNull
					.hasFieldOrPropertyWithValue(SsmSessionState::current.name, props["current"]?.toInt())
					.hasFieldOrPropertyWithValue(SsmSessionState::iteration.name, props["iteration"]?.toInt())
					.hasFieldOrPropertyWithValue(SsmSessionState::origin.name, props["origin"]?.toInt())
					.hasFieldOrPropertyWithValue(SsmSessionState::ssm.name, props["ssm"]?.contextualize())
			}
		}
	}

	private fun String.contextualize() = "$this-$uuid"
}