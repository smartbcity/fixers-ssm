package ssm.tx.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.tx.dsl.config.TxSsmConfig

/**
 * @d2 model
 * @child [TxSsmConfig]
 * @child [ssm.couchdb.autoconfiguration.SsmCouchdbProperties]
 * @title SSM Configuration List
 * @page
 * The SDK provides an auto-configuration module for spring applications. \
 * The configuration is composed of two lists of [SSM Configurations][SsmConfigProperties] and [CouchDB Configurations][ssm.couchdb.autoconfiguration.SsmCouchdbProperties]
 * @@title SSM-API/Configuration
 * @@example {
 * 	ssm: {
 *		couchdb: {
 *			smartbase: {
 *				url: "http://peer.sandbox.smartb.network:9000",
 *				username: "admin",
 *				password: "admin",
 *				serviceName: "ssm-couchdb"
 *			}
 *		},
 * 		list: {
 *		 	ProductLogistic: {
 *			 	"1.0": {
 *				 	baseUrl: "http://peer.sandbox.smartb.network:9000",
 *				 	channel: "channel-smartb",
 *				 	chaincode: "ssm-smartb",
 *				 	couchdb: "smartbase",
 *				 	dbName: "smartbase_ssm"
 *				}
 *			}
 *		}
 *	}
 * }
 */
@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
class SsmConfigProperties(
	val list: TxSsmConfig,
)