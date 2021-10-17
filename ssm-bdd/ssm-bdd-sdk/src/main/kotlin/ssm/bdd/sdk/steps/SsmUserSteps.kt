package ssm.bdd.sdk.steps

import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import ssm.bdd.sdk.SsmClientTestBuilder
import ssm.sdk.client.extention.loadFromFile
import ssm.chaincode.client.features.SsmCucumberBag
import ssm.chaincode.client.features.contextualize
import ssm.chaincode.dsl.model.SsmAgentName
import ssm.sdk.sign.model.SignerAdmin

class SsmUserSteps : En {

	lateinit var bag: SsmCucumberBag

	init {
		Before { scenario: Scenario ->
			bag = SsmCucumberBag.init(scenario)
		}

		When("I fetch the list of admin") {
			runBlocking {
				bag.admins = bag.client.listAdmins()
			}
		}

		When("I fetch the list of user") {
			runBlocking {
				bag.agents = bag.client.listAgent()
			}
		}

		When("I register a user with name {string}") { agentName: SsmAgentName ->
			runBlocking {
				val agent = loadFromFile(agentName.contextualize(bag), SsmClientTestBuilder.USER1_FILENAME)
				val transactionEvent = bag.client.registerUser(
					bag.adminSigner,
					agent
				)!!
				bag.transactions[agentName.contextualize(bag)] = transactionEvent
			}
		}

		Then("Admin with name {string} must be in the list") { agentName: SsmAgentName ->
			Assertions.assertThat(bag.admins).contains(agentName)
		}

		Then("Agent with name {string} must be in the list") { agentName: SsmAgentName ->
			Assertions.assertThat(bag.agents).contains(agentName.contextualize(bag))
		}

		Then("Agent with name {string} must not be in the list") { agentName: SsmAgentName ->
			Assertions.assertThat(bag.agents).doesNotContain(agentName.contextualize(bag))
		}

		Given("An admin signer with name {string}") { agentName: SsmAgentName ->
			bag.adminSigner = SignerAdmin.loadFromFile(SsmClientTestBuilder.ADMIN_NAME, "${SsmCucumberBag.NETWORK}/$agentName")
		}
	}

}
