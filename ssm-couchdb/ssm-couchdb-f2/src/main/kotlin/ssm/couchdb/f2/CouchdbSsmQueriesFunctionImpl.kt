package ssm.couchdb.f2

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
import ssm.couchdb.f2.query.CouchDbDatabaseGetChangesQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbAdminListQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbChaincodeListQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbDatabaseGetQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbDatabaseListQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbSsmGetQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbSsmListQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbSsmSessionStateGetQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbSsmSessionStateListQueryFunctionImpl
import ssm.couchdb.f2.query.CouchdbUserListQueryFunctionImpl

class CouchdbSsmQueriesFunctionImpl : CouchDbSsmQueries {
	override fun couchdbDatabaseGetChangesQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetChangesQueryFunction {
		return CouchDbDatabaseGetChangesQueryFunctionImpl(config).couchDbDatabaseGetChangesQueryFunction()
	}

	override fun couchdbDatabaseListQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseListQueryFunction {
		return CouchdbDatabaseListQueryFunctionImpl(config).couchdbDatabaseListQueryFunction()
	}

	override fun couchdbDatabaseGetQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetQueryFunction {
		return CouchdbDatabaseGetQueryFunctionImpl(config).couchdbDatabaseGetQueryFunction()
	}

	override fun couchdbChaincodeListQueryFunction(config: SsmCouchdbConfig): CouchdbChaincodeListQueryFunction {
		return CouchdbChaincodeListQueryFunctionImpl(config).couchdbChaincodeListQueryFunction()
	}

	override fun couchdbAdminListQueryFunction(config: SsmCouchdbConfig): CouchdbAdminListQueryFunction {
		return CouchdbAdminListQueryFunctionImpl(config).couchdbAdminListQueryFunction()
	}

	override fun couchdbUserListQueryFunction(config: SsmCouchdbConfig): CouchdbUserListQueryFunction {
		return CouchdbUserListQueryFunctionImpl(config).couchdbUserListQueryFunction()
	}

	override fun couchdbSsmGetQueryFunction(config: SsmCouchdbConfig): CouchdbSsmGetQueryFunction {
		return CouchdbSsmGetQueryFunctionImpl(config).couchdbSsmGetQueryFunction()
	}

	override fun couchdbSsmListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmListQueryFunction {
		return CouchdbSsmListQueryFunctionImpl(config).couchdbSsmListQueryFunction()
	}

	override fun couchdbSsmSessionStateListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmSessionStateListQueryFunction {
		return CouchdbSsmSessionStateListQueryFunctionImpl(config).couchdbSsmSessionStateListQueryFunction()
	}


	override fun couchdbSsmSessionStateGetQueryFunction(config: SsmCouchdbConfig): CouchdbSsmSessionStateGetQueryFunction {
		return CouchdbSsmSessionStateGetQueryFunctionImpl(config).couchdbSsmSessionStateGetQueryFunction()
	}

}
