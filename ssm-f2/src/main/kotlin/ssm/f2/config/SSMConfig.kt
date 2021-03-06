package ssm.f2.config

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.KotlinModule
import ssm.client.SsmClient
import ssm.client.SsmClientConfig
import ssm.client.domain.Signer
import ssm.client.domain.SignerAdmin
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SSMConfig {

	@Value("\${f2.ssm.signer.admin.name}")
	private lateinit var signerAdminName: String

	@Value("\${f2.ssm.signer.admin.key}")
	private lateinit var signerAdminKey: String

	@Value("\${f2.ssm.signer.user.name}")
	private lateinit var signerUserName: String

	@Value("\${f2.ssm.signer.user.key}")
	private lateinit var signerUserKey: String

	@Value("\${f2.ssm.baseUrl}")
	private lateinit var ssmBaseUrl: String

	@Bean
	fun signer(): Signer {
		return Signer.loadFromFile(signerUserName, signerUserKey)
	}

	@Bean
	fun ssmClient(): SsmClient {
		val ssmClientConfig = SsmClientConfig(ssmBaseUrl)
		return SsmClient.fromConfig(ssmClientConfig)
	}

	@Bean
	fun signerAdmin(): SignerAdmin {
		val signe = Signer.loadFromFile(signerAdminName, signerAdminKey)
		return SignerAdmin(signe)
	}

}