package ssm.couchdb.dsl

import f2.dsl.cqrs.Query

expect interface CdbQueryDTO: Query {
    val dbConfig: String
}
