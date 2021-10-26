package ssm.bdd.config

import io.cucumber.java8.Scenario
import java.util.UUID
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.SsmAgentName
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.chaincode.dsl.model.uri.SsmUri
import ssm.sdk.client.SsmClientBuilder
import ssm.sdk.client.SsmClientConfig
import ssm.sdk.sign.model.Signer
import ssm.sdk.sign.model.SignerAdmin
import ssm.sdk.sign.model.SignerName

class SsmCucumberBag(
	val config: SsmClientConfig
) {

	companion object {
		val cucumbers: MutableMap<String, SsmCucumberBag> = mutableMapOf()

		fun init(scenario: Scenario): SsmCucumberBag {
			if (!cucumbers.containsKey(scenario.id)) {
				cucumbers[scenario.id] = SsmCucumberBag(SsmClientConfig("http://localhost:9090")).apply {
					uuid = UUID.randomUUID().toString()
				}
			}
			return cucumbers[scenario.id]!!
		}

	}

	lateinit var uuid: String

	val client = SsmClientBuilder.builder(config).withChannelId("sandbox").withSsmId("ssm").build()

	// URI
	lateinit var ssmUri: SsmUri
	val chaincodeUri: ChaincodeUri = "chaincode:sandbox:ssm"

	//	SsmSdkTxSteps
	lateinit var adminSigner: SignerAdmin
	var userSigners: MutableMap<SignerName, Signer> = mutableMapOf()
	var ssms: MutableMap<SsmName, Ssm> = mutableMapOf()

	// SsmChaincodeBddSteps
	var adminsName: List<SsmAgentName> = emptyList()
	var usersName: List<SsmAgentName> = emptyList()
	var ssmsName: List<SsmName> = emptyList()
	var sessions: List<String> = emptyList()


}

fun String.contextualize(bag: SsmCucumberBag) = "$this-${bag.uuid}"
