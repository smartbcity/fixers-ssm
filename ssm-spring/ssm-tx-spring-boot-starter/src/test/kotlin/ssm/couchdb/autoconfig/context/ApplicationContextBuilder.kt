package ssm.couchdb.autoconfig.context

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import ssm.tx.dsl.config.SsmTxConfig


class ApplicationContextBuilder {

	fun create(types: Array<Class<*>>, profile: Array<String> = emptyArray(), config: SsmTxConfig?): GenericApplicationContext {
		return SpringApplicationBuilder(*types)
			.profiles(*profile)
			.withCouchdbConfig(config)
			.run() as GenericApplicationContext
	}

	private fun SpringApplicationBuilder.withCouchdbConfig(config: SsmTxConfig?): SpringApplicationBuilder {
		return config?.let {
			properties(
				"ssm.tx.chaincode.url=${config.chaincode.url}",
				"ssm.tx.couchdb.url=${config.couchdb.url}",
				"ssm.tx.couchdb.username=${config.couchdb.username}",
				"ssm.tx.couchdb.password=${config.couchdb.password}",
				"ssm.tx.couchdb.serviceName=${config.couchdb.serviceName}"
			)
		} ?: this
	}

	@EnableAutoConfiguration
	@Configuration
	class SimpleConfiguration


}
