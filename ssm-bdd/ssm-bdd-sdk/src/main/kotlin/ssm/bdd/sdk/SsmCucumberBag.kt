package ssm.chaincode.client.features

import io.cucumber.java8.Scenario
import java.util.UUID
import ssm.bdd.sdk.SsmClientTestBuilder
import ssm.chaincode.dsl.model.InvokeReturn
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgentName
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSession
import ssm.sdk.sign.model.SignerAdmin

class SsmCucumberBag {

	companion object {
		const val NETWORK = "bclan-it"
		val cucumbers: MutableMap<String, SsmCucumberBag> = mutableMapOf()

		fun init(scenario: Scenario): SsmCucumberBag {
			if(!cucumbers.containsKey(scenario.id)) {
				cucumbers[scenario.id] = SsmCucumberBag().apply {
					uuid = UUID.randomUUID().toString()
				}
			}
			return cucumbers[scenario.id]!!
		}

	}
	lateinit var uuid: String

	val client = SsmClientTestBuilder.build()

	val transactions = mutableMapOf<String, InvokeReturn>()

	lateinit var adminSigner: SignerAdmin

	lateinit var ssmSchema: Ssm
	lateinit var ssmSession: SsmSession

	var ssms: List<SsmName> = emptyList()

	var admins: List<SsmAgentName> = emptyList()
	var agents: List<SsmAgentName> = emptyList()

}

fun String.contextualize(bag: SsmCucumberBag) = "$this-${bag.uuid}"
