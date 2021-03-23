package ssm.f2.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.client.SsmClient
import ssm.client.SsmClientConfig
import ssm.client.domain.Signer
import ssm.client.domain.SignerAdmin
import java.io.IOException

@Configuration
@ConditionalOnProperty(prefix = "ssm.f2", name = ["baseUrl"])
@EnableConfigurationProperties(SsmF2Properties::class)
class SsmF2Configuration {

	@Autowired
	lateinit var ssmF2Properties: SsmF2Properties

	@Bean
	protected fun ssmClientConfig(): SsmClientConfig? {
		return SsmClientConfig(ssmF2Properties.baseUrl)
	}

	@Bean
	@Throws(IOException::class)
	protected fun ssmClient(ssmClientConfig: SsmClientConfig): SsmClient {
		return SsmClient.fromConfig(ssmClientConfig)
	}


	@ConditionalOnProperty(prefix = "ssm.f2.signer.user", name = ["name", "key"])
	@Bean
	fun signer(): Signer {
		return Signer.loadFromFile(ssmF2Properties.signer.user!!.name, ssmF2Properties.signer.user!!.key)
	}

	@Bean
	fun ssmClient(): SsmClient {
		val ssmClientConfig = SsmClientConfig(ssmF2Properties.baseUrl)
		return SsmClient.fromConfig(ssmClientConfig)
	}

	@ConditionalOnProperty(prefix = "ssm.f2.signer.admin", name = ["name", "key"])
	@Bean
	fun signerAdmin(): SignerAdmin {
		val signe = Signer.loadFromFile(ssmF2Properties.signer.admin!!.name, ssmF2Properties.signer.admin!!.key)
		return SignerAdmin(signe)
	}


}