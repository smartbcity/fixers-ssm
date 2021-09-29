package ssm.chaincode.client.invoke.query

class LogQuery : AbstractQuery(), HasGet {

	companion object {
		private const val GET_FUNCTION = "log"
	}

	override fun functionGetValue(): String {
		return GET_FUNCTION
	}
}
