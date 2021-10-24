package ssm.couchdb.dsl

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

/**
 * - fun couchDbDatabaseGetChangesQueryFunction(config: CouchdbConfig): [ssm.couchdb.dsl.query.CouchdbDatabaseGetChangesQueryFunction]
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
	fun couchdbDatabaseGetChangesQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetChangesQueryFunction
	fun couchdbDatabaseListQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseListQueryFunction
	fun couchdbDatabaseGetQueryFunction(config: SsmCouchdbConfig): CouchdbDatabaseGetQueryFunction

	fun couchdbChaincodeListQueryFunction(config: SsmCouchdbConfig): CouchdbChaincodeListQueryFunction

//	fun couchdbAdminGetQueryFunction(config: SsmCouchdbConfig): CouchdbAdminGetQueryFunction
	fun couchdbAdminListQueryFunction(config: SsmCouchdbConfig): CouchdbAdminListQueryFunction

//	fun couchdbUserGetQueryFunction(config: SsmCouchdbConfig): CouchdbUserGetQueryFunction
	fun couchdbUserListQueryFunction(config: SsmCouchdbConfig): CouchdbUserListQueryFunction

	fun couchdbSsmGetQueryFunction(config: SsmCouchdbConfig): CouchdbSsmGetQueryFunction
	fun couchdbSsmListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmListQueryFunction

	fun couchdbSsmSessionStateListQueryFunction(config: SsmCouchdbConfig): CouchdbSsmSessionStateListQueryFunction
	fun couchdbSsmSessionStateGetQueryFunction(config: SsmCouchdbConfig): CouchdbSsmSessionStateGetQueryFunction
}
