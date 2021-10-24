package ssm.couchdb.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.CouchDbSsmQueries
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.query.CouchdbAdminListQueryFunction
import ssm.couchdb.dsl.query.CouchdbChaincodeListQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseGetChangesQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction
import ssm.couchdb.dsl.query.CouchdbUserListQueryFunction
import ssm.couchdb.f2.CouchdbSsmQueriesFunctionImpl

@ConditionalOnProperty(prefix = "ssm.couchdb", name = ["url"])
@EnableConfigurationProperties(SsmCouchdbProperties::class)
@Configuration(proxyBeanMethods = false)
class SsmCouchdbAutoConfiguration : CouchDbSsmQueries {

	private val couchDbSsmQueriesImpl: CouchdbSsmQueriesFunctionImpl = CouchdbSsmQueriesFunctionImpl()

	@Bean
	fun couchdbConfig(
		ssmCouchdbProperties: SsmCouchdbProperties,
	): SsmCouchdbConfig = ssmCouchdbProperties.couchdb

	override fun couchdbDatabaseGetChangesQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetChangesQueryFunction {
		return couchDbSsmQueriesImpl.couchdbDatabaseGetChangesQueryFunction(config)
	}

	@Bean
	override fun couchdbDatabaseListQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseListQueryFunction {
		return couchDbSsmQueriesImpl.couchdbDatabaseListQueryFunction(config)
	}

	@Bean
	override fun couchdbDatabaseGetQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetQueryFunction {
		return couchDbSsmQueriesImpl.couchdbDatabaseGetQueryFunction(config)
	}

	@Bean
	override fun couchdbChaincodeListQueryFunction(config: SsmCouchdbConfig): CouchdbChaincodeListQueryFunction {
		return couchDbSsmQueriesImpl.couchdbChaincodeListQueryFunction(config)
	}

	@Bean
	override fun couchdbAdminListQueryFunction(config: SsmCouchdbConfig): CouchdbAdminListQueryFunction {
		return couchDbSsmQueriesImpl.couchdbAdminListQueryFunction(config)
	}

	@Bean
	override fun couchdbUserListQueryFunction(config: SsmCouchdbConfig): CouchdbUserListQueryFunction {
		return couchDbSsmQueriesImpl.couchdbUserListQueryFunction(config)
	}

	@Bean
	override fun couchdbSsmGetQueryFunction(config: SsmCouchdbConfig): CouchdbSsmGetQueryFunction {
		return couchDbSsmQueriesImpl.couchdbSsmGetQueryFunction(config)
	}

	@Bean
	override fun couchdbSsmListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmListQueryFunction {
		return couchDbSsmQueriesImpl.couchdbSsmListQueryFunction(config)
	}

	@Bean
	override fun couchdbSsmSessionStateListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmSessionStateListQueryFunction {
		return couchDbSsmQueriesImpl.couchdbSsmSessionStateListQueryFunction(config)
	}

	@Bean
	override fun couchdbSsmSessionStateGetQueryFunction(config: SsmCouchdbConfig): CouchdbSsmSessionStateGetQueryFunction {
		return couchDbSsmQueriesImpl.couchdbSsmSessionStateGetQueryFunction(config)
	}
}
