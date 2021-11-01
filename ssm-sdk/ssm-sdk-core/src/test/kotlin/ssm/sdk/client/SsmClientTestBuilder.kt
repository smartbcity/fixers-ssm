package ssm.sdk.client

import java.io.IOException
import java.util.UUID
import ssm.sdk.client.SsmClientBuilder.Companion.builder

object SsmClientTestBuilder {

	private val uuid = UUID.randomUUID().toString()
	private const val NETWORK = "bclan-it/"


	@Throws(IOException::class)
	fun build(): SsmClient {
		val configFileName = configFileName
		return builder(configFileName).withChannelId("sandbox").withSsmId("ssm").build()
	}

	private val configFileName: String
		get() {
			val profile = System.getenv("PROFILES_ACTIVE")
			println("////////////////////////////")
			println(profile)
			println("////////////////////////////")
			return if ("gitlab".equals(profile, ignoreCase = true)) {
				"ssm-client-gitlab.properties"
			} else "ssm-client.properties"
		}
}
