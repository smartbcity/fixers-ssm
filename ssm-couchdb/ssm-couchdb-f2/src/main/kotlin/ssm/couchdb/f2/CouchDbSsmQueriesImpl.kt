package ssm.couchdb.f2

import ssm.couchdb.dsl.CouchDbSsmQueries
import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.query.CouchDbDatabaseGetChangesQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction
import ssm.couchdb.f2.query.CouchDbDatabaseGetChangesQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbDatabaseGetQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbDatabaseListQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbSsmGetQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbSsmListQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbSsmSessionStateListQueryFunctionImpl

class CouchDbSsmQueriesImpl : CouchDbSsmQueries {
	override fun couchDbDatabaseGetChangesQueryFunction(config: SsmCouchdbConfig): CouchDbDatabaseGetChangesQueryFunction {
		return CouchDbDatabaseGetChangesQueryFunctionImpl(config).couchDbDatabaseGetChangesQueryFunction()
	}

	override fun couchdbDatabaseListQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseListQueryFunction {
		return CouchdbDatabaseListQueryFunctionImpl(config).couchdbDatabaseListQueryFunction()
	}

	override fun couchDbDatabaseGetQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetQueryFunction {
		return CouchdbDatabaseGetQueryFunctionImpl(config).couchdbDatabaseGetQueryFunction()
	}

	override fun couchdbSsmGetQueryFunction(config: SsmCouchdbConfig): CouchdbSsmGetQueryFunction {
		return CouchdbSsmGetQueryFunctionImpl(config).couchdbSsmGetQueryFunction()
	}

	override fun couchDbSsmListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmListQueryFunction {
		return CouchdbSsmListQueryFunctionImpl(config).couchdbSsmListQueryFunction()
	}

	override fun couchDbSsmSessionStateListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmSessionStateListQueryFunction {
		return CouchdbSsmSessionStateListQueryFunctionImpl(config).couchdbSsmSessionStateListQueryFunction()
	}
}
