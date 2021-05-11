package ssm.client.invoke.query;

public class LogQuery extends AbstractQuery implements HasGet, HasList {

    private static final String GET_FUNCTION = "log";

    @Override
    public String functionGetValue() {
        return GET_FUNCTION;
    }
}
