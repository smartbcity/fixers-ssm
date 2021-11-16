package ssm.couchdb.dsl

import ssm.chaincode.dsl.model.SsmDTO
import ssm.chaincode.dsl.model.SsmTransitionDTO

/**
 * @title I DON'T WANT TITLE HERE
 * @d2 model
 * @page
 * Next to the blockchain, there may be a CouchDB database duplicating some data within the chain for query optimization purpose.
 * As it is a more classic database, queries take much less time to complete but the data lose the benefits of being in a blockchain.
 * @@title SSM-COUCHDB/General
 */
interface CouchdbSsmD2

/**
 * @d2 model
 * @page
 * Configuration needed to connect to the database.
 * @@title SSM-COUCHDB/Configuration
 */
interface CouchdbSsmD2Configuration

/**
 * @d2 model
 * @page
 * Test desctiotion config
 * @@title SSM-COUCHDB/Models
 */
interface CouchdbSsmD2Model


/**
 * @d2 model
 * @page
 * Test desctiotion config
 * @@title SSM-COUCHDB/Query functions
 */
interface CouchdbSsmD2Query

/**
 * @d2 model
 * @title Ssm
 * @parent [CouchdbSsmD2Model]
 */
internal interface Ssm: SsmDTO {
	override val name: String
	override val transitions: List<SsmTransitionDTO>
}
