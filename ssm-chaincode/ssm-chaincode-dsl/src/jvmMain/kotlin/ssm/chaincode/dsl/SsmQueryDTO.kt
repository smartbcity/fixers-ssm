package ssm.chaincode.dsl

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.uri.ChaincodeUri

actual interface SsmQueryDTO : Query{
	actual val chaincodeUri: ChaincodeUri
}

actual interface SsmItemResultDTO<T> : Event {
	actual val item: T?
}

actual interface SsmItemsResultDTO<T> : Event {
	actual val items: Array<T>
}
