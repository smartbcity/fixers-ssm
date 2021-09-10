package ssm.chaincode.client

import java.io.IOException
import ssm.chaincode.client.SsmClientBuilder.Companion.builder

object SsmClientTestBuilder {
	@Throws(IOException::class)
	fun build(): SsmClient {
		val configFileName = configFileName
		return builder(configFileName).withChannelId("sandbox").withSsmId("ssm").build()
	}

	private val configFileName: String
		private get() {
			val profile = System.getenv("PROFILES_ACTIVE")
			println("////////////////////////////")
			println(profile)
			println("////////////////////////////")
			return if ("gitlab".equals(profile, ignoreCase = true)) {
				"ssm-client-gitlab.properties"
			} else "ssm-client.properties"
		}
}