package ssm.couchdb.dsl

import ssm.couchdb.dsl.config.CouchdbConfig
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction

/**
 * - fun couchdbDatabaseListQueryFunction(): [ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction]
 * - fun couchDbDatabaseGetQueryFunction(): [ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction]
 * - fun couchDbSsmListQueryFunction(): [ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction]
 * - fun couchDbSsmSessionStateListQueryFunction(): [ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction]
 * @d2 model
 * @title Query function
 * @parent [SsmCouchdbD2]
 */
interface CouchDbSsmQueries {
	fun couchdbDatabaseListQueryFunction(config: CouchdbConfig): CouchdbDatabaseListQueryFunction
	fun couchDbDatabaseGetQueryFunction(config: CouchdbConfig): CouchdbDatabaseGetQueryFunction
	fun couchDbSsmListQueryFunction(config: CouchdbConfig): CouchdbSsmListQueryFunction
	fun couchDbSsmSessionStateListQueryFunction(config: CouchdbConfig): CouchdbSsmSessionStateListQueryFunction
}
