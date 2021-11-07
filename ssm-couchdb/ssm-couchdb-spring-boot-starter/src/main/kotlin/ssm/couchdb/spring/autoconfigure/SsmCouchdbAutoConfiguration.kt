package ssm.couchdb.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.SsmCouchDbQueries
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
class SsmCouchdbAutoConfiguration {

	@Bean
	fun couchdbConfig(
		ssmCouchdbProperties: SsmCouchdbProperties,
	): SsmCouchdbConfig = ssmCouchdbProperties.couchdb

	@Bean
	fun ssmCouchDbQueries(
		ssmCouchdbConfig: SsmCouchdbConfig,
	): SsmCouchDbQueries = CouchdbSsmQueriesFunctionImpl(ssmCouchdbConfig)

}

@ConditionalOnBean(SsmCouchdbConfig::class)
@Configuration(proxyBeanMethods = false)
class SsmCouchdbF2AutoConfiguration(
	private val ssmCouchDbQueries: SsmCouchDbQueries
) : SsmCouchDbQueries {

	@Bean
	override fun couchdbDatabaseGetChangesQueryFunction(): CouchdbDatabaseGetChangesQueryFunction {
		return ssmCouchDbQueries.couchdbDatabaseGetChangesQueryFunction()
	}

	@Bean
	override fun couchdbDatabaseListQueryFunction(): CouchdbDatabaseListQueryFunction {
		return ssmCouchDbQueries.couchdbDatabaseListQueryFunction()
	}

	@Bean
	override fun couchdbDatabaseGetQueryFunction(): CouchdbDatabaseGetQueryFunction {
		return ssmCouchDbQueries.couchdbDatabaseGetQueryFunction()
	}

	@Bean
	override fun couchdbChaincodeListQueryFunction(): CouchdbChaincodeListQueryFunction {
		return ssmCouchDbQueries.couchdbChaincodeListQueryFunction()
	}

	@Bean
	override fun couchdbAdminListQueryFunction(): CouchdbAdminListQueryFunction {
		return ssmCouchDbQueries.couchdbAdminListQueryFunction()
	}

	@Bean
	override fun couchdbUserListQueryFunction(): CouchdbUserListQueryFunction {
		return ssmCouchDbQueries.couchdbUserListQueryFunction()
	}

	@Bean
	override fun couchdbSsmGetQueryFunction(): CouchdbSsmGetQueryFunction {
		return ssmCouchDbQueries.couchdbSsmGetQueryFunction()
	}

	@Bean
	override fun couchdbSsmListQueryFunction(): CouchdbSsmListQueryFunction {
		return ssmCouchDbQueries.couchdbSsmListQueryFunction()
	}

	@Bean
	override fun couchdbSsmSessionStateListQueryFunction(): CouchdbSsmSessionStateListQueryFunction {
		return ssmCouchDbQueries.couchdbSsmSessionStateListQueryFunction()
	}

	@Bean
	override fun couchdbSsmSessionStateGetQueryFunction(): CouchdbSsmSessionStateGetQueryFunction {
		return ssmCouchDbQueries.couchdbSsmSessionStateGetQueryFunction()
	}
}
