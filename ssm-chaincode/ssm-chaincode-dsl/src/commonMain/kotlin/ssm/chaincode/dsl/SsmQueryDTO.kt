package ssm.chaincode.dsl

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query

expect interface SsmQueryDTO : Query

expect interface SsmItemResultDTO<T> : Event {
	val item: T?
}
expect interface SsmItemsResultDTO<T> : Event {
	val items: Array<T>
}


