package ssm.bdd.couchdb

import io.cucumber.java8.En

class SsmCouchdbSteps : En {
	init {
		When("I get the list of user") {
			TODO("Not yet implemented")
		}
		When("I get the list of admin") {
			TODO("Not yet implemented")
		}
		When("I get the list of ssm") {
			TODO("Not yet implemented")
		}
		When("I get the list of session") {
			TODO("Not yet implemented")
		}
		Then("User {string} is in the list") { userName: String ->
			TODO("Not yet implemented")
		}
		Then("Admin {string} is in the list") { adminName: String ->
			TODO("Not yet implemented")
		}
		Then("Ssm {string} is in the list") { ssmName: String ->
			TODO("Not yet implemented")
		}
		Then("Session {string} is in the list") { sessionName: String ->
			TODO("Not yet implemented")
		}
		Then("Session {string} have current state origin {string} current {string} iteration {string}") {
				sessionName: String, origin: String,current: String,iteration: String ->
			TODO("Not yet implemented")
		}
	}
}
