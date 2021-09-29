package ssm.chaincode.client.invoke.query

import ssm.chaincode.client.invoke.command.InvokeArgs

abstract class AbstractQuery {
	abstract fun functionGetValue(): String
	fun queryArgs(username: String): InvokeArgs {
		return InvokeArgs(functionGetValue(), listOf(username))
	}

	fun listArgs(): InvokeArgs {
		return InvokeArgs(HasList.LIST_FUNCTION, listOf(functionGetValue()))
	}
}
