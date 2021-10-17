package ssm.bdd.sdk

import java.io.IOException
import java.util.UUID
import ssm.sdk.client.SsmClient
import ssm.sdk.client.SsmClientBuilder.Companion.builder

object SsmClientTestBuilder {

	private val uuid = UUID.randomUUID().toString()
	private const val NETWORK = "bclan-it/"
	val USER1_NAME = "bob" + "-" + uuid
	val USER2_NAME = "sam" + "-" + uuid
	const val USER1_FILENAME = NETWORK + "bob"
	const val USER2_FILENAME = NETWORK + "sam"

	const val ADMIN_NAME = "ssm-admin"



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
