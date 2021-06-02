package ssm.client.invoke.query;

public class BlockQuery extends AbstractQuery implements HasGet {

    private static final String GET_FUNCTION = "block";

    @Override
    public String functionGetValue() {
        return GET_FUNCTION;
    }
}
