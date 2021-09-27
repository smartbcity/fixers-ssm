package ssm.chaincode.spring.autoconfigure.context

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import ssm.chaincode.dsl.config.SsmChaincodeConfig


class ApplicationContextBuilder {

	fun create(types: Array<Class<*>>, profile: Array<String> = emptyArray(), config: SsmChaincodeConfig?): GenericApplicationContext {
		return SpringApplicationBuilder(*types)
			.profiles(*profile)
			.withCouchdbConfig(config)
			.run() as GenericApplicationContext
	}

	private fun SpringApplicationBuilder.withCouchdbConfig(config: SsmChaincodeConfig?): SpringApplicationBuilder {
		return config?.let {
			properties(
				"ssm.chaincode.url=${config.url}",
			)
		} ?: this
	}

	@EnableAutoConfiguration
	@Configuration
	class SimpleConfiguration


}
