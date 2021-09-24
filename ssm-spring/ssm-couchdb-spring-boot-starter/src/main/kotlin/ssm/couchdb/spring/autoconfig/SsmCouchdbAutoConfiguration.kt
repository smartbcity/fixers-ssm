package ssm.couchdb.spring.autoconfig

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.CouchDbSsmQueries
import ssm.couchdb.dsl.config.CouchdbConfig
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction
import ssm.couchdb.f2.CouchDbSsmQueriesImpl

@ConditionalOnProperty(prefix = "ssm.couchdb", name = ["url"])
@EnableConfigurationProperties(SsmCouchdbProperties::class)
@Configuration
class SsmCouchdbAutoConfiguration(
	val ssmCouchdbProperties: SsmCouchdbProperties,
) : CouchDbSsmQueries {

	private val couchDbSsmQueriesImpl: CouchDbSsmQueriesImpl = CouchDbSsmQueriesImpl()

	@Bean
	fun couchdbConfig(): CouchdbConfig = ssmCouchdbProperties.couchdb

	@Bean
	override fun couchdbDatabaseListQueryFunction(config: CouchdbConfig): CouchdbDatabaseListQueryFunction {
		return couchDbSsmQueriesImpl.couchdbDatabaseListQueryFunction(config)
	}

	@Bean
	override fun couchDbDatabaseGetQueryFunction(config: CouchdbConfig): CouchdbDatabaseGetQueryFunction {
		return couchDbSsmQueriesImpl.couchDbDatabaseGetQueryFunction(config)
	}

	@Bean
	override fun couchDbSsmListQueryFunction(config: CouchdbConfig): CouchdbSsmListQueryFunction {
		return couchDbSsmQueriesImpl.couchDbSsmListQueryFunction(config)
	}

	@Bean
	override fun couchDbSsmSessionStateListQueryFunction(config: CouchdbConfig): CouchdbSsmSessionStateListQueryFunction {
		return couchDbSsmQueriesImpl.couchDbSsmSessionStateListQueryFunction(config)
	}
}
