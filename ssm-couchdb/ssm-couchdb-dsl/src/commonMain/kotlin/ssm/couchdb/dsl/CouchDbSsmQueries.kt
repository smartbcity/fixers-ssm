package ssm.couchdb.dsl

import ssm.couchdb.dsl.config.SsmCouchdbConfig
import ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction
import ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction

/**
 * - fun couchdbDatabaseListQueryFunction(config: CouchdbConfig): [ssm.couchdb.dsl.query.CouchdbDatabaseListQueryFunction]
 * - fun couchDbDatabaseGetQueryFunction(config: CouchdbConfig): [ssm.couchdb.dsl.query.CouchdbDatabaseGetQueryFunction]
 * - fun couchDbSsmListQueryFunction(config: CouchdbConfig): [ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction]
 * - fun couchDbSsmSessionStateListQueryFunction(config: CouchdbConfig): [ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction]
 * @d2 model
 * @title Query function
 * @parent [SsmCouchdbD2]
 */
interface CouchDbSsmQueries {
	fun couchdbDatabaseListQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseListQueryFunction
	fun couchDbDatabaseGetQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetQueryFunction
	fun couchDbSsmListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmListQueryFunction
	fun couchDbSsmSessionStateListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmSessionStateListQueryFunction
}
