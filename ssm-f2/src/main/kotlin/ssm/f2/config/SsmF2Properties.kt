package ssm.f2.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "ssm.f2")
class SsmF2Properties(
	val baseUrl: String,
	val signer: SignerProperties
) {

	class SignerProperties(
		val admin: SignerUserProperties?,
		val user: SignerUserProperties?
	)

	class SignerUserProperties(
		val name: String? = null,
		val key: String? = null
	)


}