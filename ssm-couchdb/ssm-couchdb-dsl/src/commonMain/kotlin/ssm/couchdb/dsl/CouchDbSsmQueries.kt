package ssm.couchdb.dsl

import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.query.CouchDbDatabaseGetChangesQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction

/**
 * - fun couchDbDatabaseGetChangesQueryFunction(config: CouchdbConfig): [ssm.couchdb.dsl.query.CouchDbDatabaseGetChangesQueryFunction]
 * - fun couchdbDatabaseListQueryFunction(config: CouchdbConfig): [ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction]
 * - fun couchDbDatabaseGetQueryFunction(config: CouchdbConfig): [ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction]
 * - fun couchdbSsmGetQueryFunction(config: CouchdbConfig): [ssm.couchdb.dsl.query.CouchdbSsmGetQueryFunction]
 * - fun couchDbSsmListQueryFunction(config: CouchdbConfig): [ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction]
 * - fun couchDbSsmSessionStateListQueryFunction(config: CouchdbConfig): [ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction]
 * @d2 model
 * @title Query function
 * @parent [SsmCouchdbD2]
 */
interface CouchDbSsmQueries {
	fun couchDbDatabaseGetChangesQueryFunction(config: SsmCouchdbConfig): CouchDbDatabaseGetChangesQueryFunction
	fun couchdbDatabaseListQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseListQueryFunction
	fun couchDbDatabaseGetQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetQueryFunction
	fun couchdbSsmGetQueryFunction(config: SsmCouchdbConfig): CouchdbSsmGetQueryFunction
	fun couchDbSsmListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmListQueryFunction
	fun couchDbSsmSessionStateListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmSessionStateListQueryFunction
}
