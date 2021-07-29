package ssm.couchdb.dsl

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName


typealias DatabaseName = String

expect interface DatabaseDTO {
	val name: DatabaseName
}

@Serializable
@JsExport
@JsName("Database")
class Database(
	override val name: DatabaseName
): DatabaseDTO