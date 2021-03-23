package ssm.client.invoke.query;

public class SessionQuery extends AbstractQuery implements HasGet, HasList {

    private static final String GET_FUNCTION = "session";

    @Override
    public String functionGetValue() {
        return GET_FUNCTION;
    }
}