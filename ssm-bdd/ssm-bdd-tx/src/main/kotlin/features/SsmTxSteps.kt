package features

import SsmCucumberBag
import io.cucumber.java8.En
import ssm.bdd.sdk.SsmClientTestBuilder

class SsmTxSteps : En {

	val bag = SsmCucumberBag()

	init {
		Given("configuration for ssm chaincode with uri {string}") { chaincodeUri: String ->
			bag.withCLient(
				SsmClientTestBuilder.build()
			)
		}

		When("I create a ssm {string} with configuration file {string}") { ssmName: String, ssmConfig: String ->
			TODO("Not yet implemented")
		}
		When("I start a session {string} for {string}") { sessionName: String, ssmName: String ->
			TODO("Not yet implemented")
		}
		When("I perform action {string} for {string}") { action: String, sessionName: String ->
			TODO("Not yet implemented")
		}
	}
}
