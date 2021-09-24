package ssm.couchdb.f2

import ssm.couchdb.dsl.CouchDbSsmQueries
import ssm.couchdb.dsl.config.CouchdbConfig
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction
import ssm.couchdb.f2.query.CouchdbDatabaseGetQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbDatabaseListQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbSsmListQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbSsmSessionStateListQueryFunctionImpl

class CouchDbSsmQueriesImpl : CouchDbSsmQueries {

	override fun couchdbDatabaseListQueryFunction(config: CouchdbConfig): CouchdbDatabaseListQueryFunction {
		return CouchdbDatabaseListQueryFunctionImpl(config).couchdbDatabaseListQueryFunction()
	}

	override fun couchDbDatabaseGetQueryFunction(config: CouchdbConfig): CouchdbDatabaseGetQueryFunction {
		return CouchdbDatabaseGetQueryFunctionImpl(config).couchdbDatabaseGetQueryFunction()
	}

	override fun couchDbSsmListQueryFunction(config: CouchdbConfig): CouchdbSsmListQueryFunction {
		return CouchdbSsmListQueryFunctionImpl(config).couchdbSsmListQueryFunction()
	}

	override fun couchDbSsmSessionStateListQueryFunction(config: CouchdbConfig): CouchdbSsmSessionStateListQueryFunction {
		return CouchdbSsmSessionStateListQueryFunctionImpl(config).couchdbSsmSessionStateListQueryFunction()
	}
}
