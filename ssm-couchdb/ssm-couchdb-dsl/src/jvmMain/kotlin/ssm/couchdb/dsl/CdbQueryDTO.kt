package ssm.couchdb.dsl

import f2.dsl.cqrs.Query

actual interface CdbQueryDTO : Query {
	actual val dbConfig: String
}
