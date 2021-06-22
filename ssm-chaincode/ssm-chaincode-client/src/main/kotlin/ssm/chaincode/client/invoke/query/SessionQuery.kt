package ssm.chaincode.client.invoke.query

class SessionQuery : AbstractQuery(), HasGet, HasList {

    companion object {
        private const val GET_FUNCTION = "session"
    }

    override fun functionGetValue(): String {
        return GET_FUNCTION
    }
}