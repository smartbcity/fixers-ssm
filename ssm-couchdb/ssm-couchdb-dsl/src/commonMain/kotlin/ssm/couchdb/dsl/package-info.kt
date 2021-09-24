package ssm.couchdb.dsl

import ssm.chaincode.dsl.SsmDTO
import ssm.chaincode.dsl.SsmTransitionDTO

/**
 * @title I DON'T WANT TITLE HERE
 * @d2 model
 * @page
 * Next to the blockchain, there may be a CouchDB database duplicating some data within the chain for query optimization purpose.
 * As it is a more classic database, queries take much less time to complete but the data lose the benefits of being in a blockchain.
 * @@title SSM-CouchDB/General
 */
interface SsmCouchdbD2

/**
 * @d2 model
 * @title Models
 * @parent [SsmCouchdbD2]
 */
interface SsmCouchdbD2Model

/**
 * @d2 model
 * @title Query function
 * @parent [SsmCouchdbD2]
 */
interface SsmCouchdbD2Query

/**
 * @d2 model
 * @title Ssm
 * @parent [SsmCouchdbD2Model]
 */
internal interface Ssm: SsmDTO {
	override val name: String
	override val transitions: List<SsmTransitionDTO>
}
