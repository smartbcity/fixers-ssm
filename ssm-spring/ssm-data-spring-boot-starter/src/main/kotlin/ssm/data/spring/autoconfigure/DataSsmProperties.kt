package ssm.data.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.couchdb.dsl.config.CouchdbSsmConfig

@ConfigurationProperties(prefix = "ssm")
data class DataSsmProperties(
	/**
	 * Configuration for couchdb.
	 */
	val couchdb: CouchdbSsmConfig?,
	/**
	 *  Configuration for couchdb.
	 */
	val chaincode: ChaincodeSsmConfig?,
)
