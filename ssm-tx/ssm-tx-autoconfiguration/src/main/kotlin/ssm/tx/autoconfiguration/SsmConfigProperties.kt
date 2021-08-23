package ssm.tx.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.chaincode.dsl.SsmChaincodeProperties
import ssm.tx.dsl.config.TxSsmConfig

/**
 * @d2 model
 * @child [TxSsmConfig]
 * @child [ssm.couchdb.autoconfiguration.SsmCouchdbProperties]
 * @title SSM Configuration List
 * @page
 * The SDK provides an auto-configuration module for spring applications. \
 * The configuration is composed of two lists of [SSM Configurations][SsmConfigProperties] and [CouchDB Configurations][ssm.couchdb.autoconfiguration.SsmCouchdbProperties]
 * @@title SSM-TX/Configuration
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
 *		chaincode: {
 *			"smartcode-ssm": {
 *				baseUrl: "http://peer.sandbox.smartb.network:9000",
 *	 			channelId: "channel-smartb",
 *	 			chaincodeId: "ssm-smartb"
 *	 		}
 *		},
 * 		list: {
 *		 	ProductLogistic: {
 *			 	"1.0": {
 *				 	baseUrl: "http://peer.sandbox.smartb.network:9000",
 *				 	channel: "channel-smartb",
 *				 	chaincode: "smartcode-ssm"
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
	val chaincode: Map<String, SsmChaincodeProperties>
)