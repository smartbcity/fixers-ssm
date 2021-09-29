package ssm.chaincode.client.invoke.query

class TransactionQuery : AbstractQuery(), HasGet {

	companion object {
		private const val GET_FUNCTION = "transaction"
	}

	override fun functionGetValue(): String {
		return GET_FUNCTION
	}
}
