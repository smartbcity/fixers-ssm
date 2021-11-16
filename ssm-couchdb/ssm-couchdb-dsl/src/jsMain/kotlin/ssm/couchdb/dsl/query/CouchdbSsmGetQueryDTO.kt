package ssm.couchdb.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.ChaincodeId
import ssm.chaincode.dsl.model.ChannelId
import ssm.chaincode.dsl.model.SsmDTO
import ssm.chaincode.dsl.model.SsmName
import ssm.chaincode.dsl.model.uri.SsmUri

@JsName("CouchdbSsmGetQueryDTO")
@JsExport
actual external interface CouchdbSsmGetQueryDTO : Query {
	actual val channelId: ChannelId
	actual val chaincodeId: ChaincodeId
	actual val ssmName: SsmName
}

@JsName("CouchdbSsmGetQueryDTO")
@JsExport
actual external interface CouchdbSsmGetQueryResultDTO: Event {
	actual val item: SsmDTO?
	actual val uri: SsmUri
}
