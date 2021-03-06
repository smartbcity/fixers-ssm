package ssm.client.invoke.query;

public class AdminQuery extends AbstractQuery implements HasGet, HasList {

    private static final String GET_FUNCTION = "admin";

    @Override
    public String functionGetValue() {
        return GET_FUNCTION;
    }
}
