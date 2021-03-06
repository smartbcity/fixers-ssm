package ssm.client.invoke.query;

import ssm.client.invoke.command.InvokeArgs;

public abstract class AbstractQuery {

    public abstract String functionGetValue();

    public InvokeArgs queryArgs(String username) {
        return new InvokeArgs(functionGetValue(), username);
    }

    public InvokeArgs listArgs() {
        return new InvokeArgs(HasList.LIST_FUNCTION, functionGetValue());
    }
}
