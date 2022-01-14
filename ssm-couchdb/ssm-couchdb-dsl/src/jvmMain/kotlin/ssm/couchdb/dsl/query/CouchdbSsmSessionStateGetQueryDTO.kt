package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.SsmSessionStateDTO
import ssm.chaincode.dsl.model.uri.ChaincodeUriDTO

actual interface CouchdbSsmSessionStateGetQueryDTO : Query {
	actual val chaincodeUri: ChaincodeUriDTO
	actual val ssmName: SsmName?
	actual val sessionName: SessionName
}

actual interface CouchdbSsmSessionStateGetQueryResultDTO : Event {
	actual val item: SsmSessionStateDTO
}
