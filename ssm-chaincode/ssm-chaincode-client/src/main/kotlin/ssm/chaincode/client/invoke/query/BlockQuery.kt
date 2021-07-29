package ssm.chaincode.client.invoke.query

class BlockQuery : AbstractQuery(), HasGet {

    companion object {
        private const val GET_FUNCTION = "block"
    }

    override fun functionGetValue(): String {
        return GET_FUNCTION
    }
}