package ssm.couchdb.dsl

import f2.dsl.cqrs.Query

expect interface CdbQueryDTO : Query {
	/**
	 * Name of the config of the database to query on
	 * @example "smartbase"
	 */
	val dbConfig: String
}
