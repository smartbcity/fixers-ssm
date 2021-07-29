package ssm.chaincode.client.invoke.query

class AgentQuery : AbstractQuery(), HasGet, HasList {

    companion object {
        const val GET_FUNCTION = "user"
    }

    override fun functionGetValue(): String {
        return AgentQuery.GET_FUNCTION
    }

}