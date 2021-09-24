package ssm.couchdb.dsl.model

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

typealias DatabaseName = String

/**
 * Information about a couchdb database.
 * TODO Use SHOUlD NOT BE H2 BU H3
 * @d2 query
 * @title Database
 * @parent [ssm.couchdb.dsl.SsmCouchdbD2Model]
 */
expect interface DatabaseDTO {
	/**
	 * Name of the database
	 * @example "smartbase_ssm"
	 */
	val name: DatabaseName
}

@Serializable
@JsExport
@JsName("Database")
class Database(
	override val name: DatabaseName,
) : DatabaseDTO
