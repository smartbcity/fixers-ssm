package ssm.chaincode.client.invoke.query

class SsmQuery : AbstractQuery(), HasGet, HasList {

    companion object {
        private const val GET_FUNCTION = "ssm"
    }

    override fun functionGetValue(): String {
        return GET_FUNCTION
    }
}