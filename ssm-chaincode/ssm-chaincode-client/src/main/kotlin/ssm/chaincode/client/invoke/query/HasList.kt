package ssm.chaincode.client.invoke.query

import ssm.chaincode.client.invoke.command.InvokeArgs

interface HasList {
	fun listArgs(): InvokeArgs

	companion object {
		const val LIST_FUNCTION = "list"
	}
}
