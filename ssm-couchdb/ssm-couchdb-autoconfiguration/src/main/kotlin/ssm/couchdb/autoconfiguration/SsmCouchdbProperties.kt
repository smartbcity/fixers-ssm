package ssm.couchdb.autoconfiguration

import org.springframework.boot.context.properties.ConstructorBinding

/**
 * Describes how to access a CouchDB service.
 * @d2 model
 * @title CouchDB Configuration List
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
 */
@ConstructorBinding
data class SsmCouchdbProperties(
	/**
	 * URL of the machine hosting the database
	 * @example "http://peer.sandbox.smartb.network:9000"
	 */
	val url: String,

	/**
	 * Username to log into the service handling the database
	 * @example "admin"
	 */
	val username: String,

	/**
	 * Password to log into the service handling the database
	 * @example "admin"
	 */
	val password: String,

	/**
	 * Name of the service handling the database
	 * @example "ssm-couchdb"
	 */
	val serviceName: String,
)
