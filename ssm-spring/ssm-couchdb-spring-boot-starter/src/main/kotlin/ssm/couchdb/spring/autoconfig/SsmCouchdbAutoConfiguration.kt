package ssm.couchdb.spring.autoconfig

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.CouchDbSsmQueries
import ssm.couchdb.dsl.config.SsmCouchdbConfig
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
	fun couchdbConfig(): SsmCouchdbConfig = ssmCouchdbProperties.couchdb

	@Bean
	override fun couchdbDatabaseListQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseListQueryFunction {
		return couchDbSsmQueriesImpl.couchdbDatabaseListQueryFunction(config)
	}

	@Bean
	override fun couchDbDatabaseGetQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetQueryFunction {
		return couchDbSsmQueriesImpl.couchDbDatabaseGetQueryFunction(config)
	}

	@Bean
	override fun couchDbSsmListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmListQueryFunction {
		return couchDbSsmQueriesImpl.couchDbSsmListQueryFunction(config)
	}

	@Bean
	override fun couchDbSsmSessionStateListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmSessionStateListQueryFunction {
		return couchDbSsmQueriesImpl.couchDbSsmSessionStateListQueryFunction(config)
	}
}
