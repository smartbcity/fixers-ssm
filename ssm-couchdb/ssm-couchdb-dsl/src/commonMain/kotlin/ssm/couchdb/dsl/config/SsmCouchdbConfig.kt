package ssm.couchdb.dsl.config

/**
 * Properties need to connect to couchdb.
 * @d2 model
 * @title CouchdbConfig
 * @parent [ssm.couchdb.dsl.SsmCouchdbD2Configuration]
 */
data class SsmCouchdbConfig(
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
