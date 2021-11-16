package ssm.couchdb.spring.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.SsmCouchDbQueries
import ssm.couchdb.dsl.config.CouchdbSsmConfig
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
@EnableConfigurationProperties(CouchdbSsmProperties::class)
@Configuration(proxyBeanMethods = false)
class CouchdbSsmAutoConfiguration {

	@Bean
	fun couchdbConfig(
		ssmCouchdbProperties: CouchdbSsmProperties,
	): CouchdbSsmConfig = ssmCouchdbProperties.couchdb

	@Bean
	fun ssmCouchDbQueries(
		ssmCouchdbConfig: CouchdbSsmConfig,
	): SsmCouchDbQueries = CouchdbSsmQueriesFunctionImpl(ssmCouchdbConfig)

}

@ConditionalOnBean(CouchdbSsmConfig::class)
@Configuration(proxyBeanMethods = false)
class CouchdbSsmF2AutoConfiguration(
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
