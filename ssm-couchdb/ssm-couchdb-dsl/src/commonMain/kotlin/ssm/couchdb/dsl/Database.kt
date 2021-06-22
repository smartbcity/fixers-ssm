package ssm.couchdb.dsl


typealias DatabaseName = String

interface DatabaseDTO {
	val name: DatabaseName
}

class Database(
	override val name: DatabaseName
): DatabaseDTO