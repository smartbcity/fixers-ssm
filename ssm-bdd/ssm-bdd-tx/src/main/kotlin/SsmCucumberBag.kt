
import ssm.bdd.sdk.SsmClientTestBuilder
import ssm.sdk.client.SsmClient

class SsmCucumberBag {
	var client = SsmClientTestBuilder.build()

	fun withCLient(client: SsmClient) {
		this.client = client
	}
}
