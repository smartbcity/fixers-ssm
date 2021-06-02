package ssm.client.invoke.query;

public class TransactionQuery extends AbstractQuery implements HasGet {

    private static final String GET_FUNCTION = "transaction";

    @Override
    public String functionGetValue() {
        return GET_FUNCTION;
    }
}
