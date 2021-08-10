package ssm.couchdb.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName


typealias DatabaseName = String

expect interface DatabaseDTO {
	/**
	 * Name of the database
	 * @example "smartbase_ssm"
	 */
	val name: DatabaseName
}

/**
 * Information about a database
 * @d2 model
 * @page
 * Next to the blockchain, there may be a CouchDB database duplicating some data within the chain for query optimization purpose.
 * As it is a more classic database, queries take much less time to complete but the data lose the benefits of being in a blockchain.
 * @@title CouchDB-DSL/General
 */
@Serializable
@JsExport
@JsName("Database")
class Database(
	override val name: DatabaseName
): DatabaseDTO