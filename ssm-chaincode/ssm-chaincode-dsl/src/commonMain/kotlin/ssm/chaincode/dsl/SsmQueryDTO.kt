package ssm.chaincode.dsl

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import ssm.chaincode.dsl.model.Ssm
import ssm.chaincode.dsl.model.uri.ChaincodeUri

expect interface SsmQueryDTO : Query {
	/**
	 * Uri of the chaincode
	 * @example "chaincode:sandbox:thessm"
	 */
	val chaincodeUri: ChaincodeUri
}

expect interface SsmItemResultDTO<T> : Event {
	val item: T?
}
expect interface SsmItemsResultDTO<T> : Event {
	val items: Array<T>
}


