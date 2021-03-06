package ssm.client.invoke.query;

import ssm.client.invoke.command.InvokeArgs;

public interface HasGet {
    InvokeArgs queryArgs(String value);
}
