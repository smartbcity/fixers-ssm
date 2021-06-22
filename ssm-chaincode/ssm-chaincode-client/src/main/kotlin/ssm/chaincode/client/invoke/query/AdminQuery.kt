package ssm.chaincode.client.invoke.query

class AdminQuery : AbstractQuery(), HasGet, HasList {

    companion object {
        private const val GET_FUNCTION = "admin"
    }

    override fun functionGetValue(): String {
        return GET_FUNCTION
    }

}