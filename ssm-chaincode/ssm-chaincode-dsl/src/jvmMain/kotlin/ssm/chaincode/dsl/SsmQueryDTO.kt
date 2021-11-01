package ssm.chaincode.dsl

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query

actual interface SsmQueryDTO : Query

actual interface SsmItemResultDTO<T> : Event {
	actual val item: T?
}

actual interface SsmItemsResultDTO<T> : Event {
	actual val items: Array<T>
}
