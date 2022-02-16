package ssm.chaincode.dsl

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.uri.ChaincodeUri

@JsExport
@JsName("SsmQueryDTO")
actual external interface SsmQueryDTO : Query {
	actual val chaincodeUri: ChaincodeUri
}

@JsExport
@JsName("SsmItemResultDTO")
actual external interface SsmItemResultDTO<T> : Event {
	actual val item: T?
}

@JsExport
@JsName("SsmItemsResultDTO")
actual external interface SsmItemsResultDTO<T> : Event {
	actual val items: Array<T>
}
