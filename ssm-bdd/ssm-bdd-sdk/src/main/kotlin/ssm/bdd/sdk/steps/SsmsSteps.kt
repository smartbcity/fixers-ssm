package ssm.bdd.sdk.steps

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.assertj.core.util.Lists
import ssm.bdd.sdk.SsmClientTestBuilder
import ssm.sdk.client.extention.loadFromFile
import ssm.chaincode.client.features.SsmCucumberBag
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSession
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.SsmTransition

class SsmsSteps : En {

	lateinit var bag: SsmCucumberBag

	init {
		Before { scenario: Scenario ->
			bag = SsmCucumberBag.init(scenario)
		}

		Given("A ssm schema with name {string}") { ssmName: String ->
			val sell = SsmTransition(0, 1, "Seller", "Sell")
			val buy = SsmTransition(1, 2, "Buyer", "Buy")
			bag.ssmSchema = Ssm(ssmName.contextualize(), Lists.newArrayList(sell, buy))
		}
		When("I create a ssm with name {string}") { ssmName: String ->
			runBlocking {
				bag.transactions[ssmName.contextualize()] = bag.client.create(bag.adminSigner, bag.ssmSchema)!!
			}
		}
		When("I fetch the list of ssm") {
			runBlocking {
				bag.ssms = bag.client.listSsm()
			}
		}
		Then("Ssm with name {string} must be in the list") { ssmName: SsmName ->
			Assertions.assertThat(bag.ssms).contains(ssmName.contextualize())
		}
		Given("A ssm with name {string}") { ssmName: SsmName ->
			val sell = SsmTransition(0, 1, "Seller", "Sell")
			val buy = SsmTransition(1, 2, "Buyer", "Buy")
			val ssmSchema = Ssm(ssmName.contextualize(), Lists.newArrayList(sell, buy))
			runBlocking {
				bag.transactions[ssmName.contextualize()] = bag.client.create(bag.adminSigner, ssmSchema)!!
			}
		}
		When("I start a session with {string} for {string}") { sessionName: SessionName, ssmName: SsmName ->
			val signerUser1 = loadFromFile(SsmClientTestBuilder.USER1_NAME, SsmClientTestBuilder.USER1_FILENAME)
			val agentUser2 = loadFromFile(SsmClientTestBuilder.USER2_NAME, SsmClientTestBuilder.USER2_FILENAME)
			val roles: Map<String, String> = mapOf(
				signerUser1.name to "Buyer", agentUser2.name to "Seller"
			)
			bag.ssmSession = SsmSession(
				ssmName.contextualize(),
				sessionName.contextualize(), roles, "Used car for 100 dollars.", emptyMap()
			)
			runBlocking {
				bag.transactions[sessionName.contextualize()] = bag.client.start(bag.adminSigner, bag.ssmSession)!!
			}
		}
		Then("A transaction must has been done for {string}") { key: String ->
			Assertions.assertThat(bag.transactions).containsKey(key.contextualize())
		}
		Then("details of session must be") { table: DataTable ->
			val props = table.asMaps<String, String>(String::class.java, String::class.java).first()
			val sessionName = props["sessionName"]!!.contextualize()
			runBlocking {
				val session = bag.client.getSession(sessionName)
				Assertions.assertThat(session)
					.isNotNull
					.hasFieldOrPropertyWithValue(SsmSessionState::current.name, props["current"]?.toInt())
					.hasFieldOrPropertyWithValue(SsmSessionState::iteration.name, props["iteration"]?.toInt())
					.hasFieldOrPropertyWithValue(SsmSessionState::origin.name, props["origin"]?.toInt())
					.hasFieldOrPropertyWithValue(SsmSessionState::ssm.name, props["ssm"]?.contextualize())
			}
		}
	}

	private fun String.contextualize() = "$this-${bag.uuid}"
}
