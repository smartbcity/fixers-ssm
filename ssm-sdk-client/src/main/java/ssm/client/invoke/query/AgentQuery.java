package ssm.client.invoke.query;

public class AgentQuery extends AbstractQuery implements HasGet, HasList {

    private static final String GET_FUNCTION = "user";

    @Override
    public String functionGetValue() {
        return GET_FUNCTION;
    }
}
