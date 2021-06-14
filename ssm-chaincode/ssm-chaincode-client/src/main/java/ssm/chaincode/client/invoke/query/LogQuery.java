package ssm.chaincode.client.invoke.query;

public class LogQuery extends AbstractQuery implements HasGet {

    private static final String GET_FUNCTION = "log";

    @Override
    public String functionGetValue() {
        return GET_FUNCTION;
    }
}
