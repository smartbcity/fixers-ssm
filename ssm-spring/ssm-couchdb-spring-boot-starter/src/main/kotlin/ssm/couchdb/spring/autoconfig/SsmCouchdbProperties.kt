package ssm.couchdb.spring.autoconfig

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.couchdb.dsl.config.CouchdbConfig

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
@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class SsmCouchdbProperties(
	val couchdb: CouchdbConfig
)
