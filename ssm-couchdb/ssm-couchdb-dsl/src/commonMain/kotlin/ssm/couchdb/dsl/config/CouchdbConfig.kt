package ssm.couchdb.dsl.config

import ssm.couchdb.dsl.SsmCouchdbD2

/**
 * Properties need to connect to couchdb.
 * @d2 model
 * @title Configuration
 * @order 20
 * @example {
 *  couchdb: {
 *      smartbase: {
 *			url: "http://peer.sandbox.smartb.network:9000",
 *			username: "admin",
 *			password: "admin",
 *			serviceName: "ssm-couchdb"
 *      }
 *  }
 * }
 * @parent [ssm.couchdb.dsl.SsmCouchdbD2]
 */
data class CouchdbConfig(
	/**
	 * URL of the machine hosting the database
	 * @example "http://peer.sandbox.smartb.network:9000"
	 */
	val url: CouchdbUrl,

	/**
	 * Username to log into the service handling the database
	 * @example "admin"
	 */
	val username: CouchdbUsername,

	/**
	 * Password to log into the service handling the database
	 * @example "admin"
	 */
	val password: CouchdbPassword,

	/**
	 * Name of the service handling the database
	 * @example "ssm-couchdb"
	 */
	val serviceName: CouchdbServiceName,
)

typealias CouchdbUrl = String
typealias CouchdbUsername = String
typealias CouchdbPassword = String
typealias CouchdbServiceName = String
