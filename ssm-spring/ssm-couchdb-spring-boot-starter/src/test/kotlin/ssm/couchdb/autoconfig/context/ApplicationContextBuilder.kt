package ssm.couchdb.autoconfig.context

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import ssm.couchdb.dsl.config.CouchdbConfig


class ApplicationContextBuilder {

	fun create(types: Array<Class<*>>, profile: Array<String> = emptyArray(), config: CouchdbConfig?): GenericApplicationContext {
		return SpringApplicationBuilder(*types)
			.profiles(*profile)
			.withCouchdbConfig(config)
			.run() as GenericApplicationContext
	}

	fun SpringApplicationBuilder.withCouchdbConfig(config: CouchdbConfig?): SpringApplicationBuilder {
		return config?.let {
			properties(
				"ssm.couchdb.url=${config.url}",
				"ssm.couchdb.username=${config.username}",
				"ssm.couchdb.password=${config.password}",
				"ssm.couchdb.serviceName=${config.serviceName}"
			)
		} ?: this
	}

	@EnableAutoConfiguration
	@Configuration
	class SimpleConfiguration


}
