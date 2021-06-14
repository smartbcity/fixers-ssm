package ssm.chaincode.client.invoke.query;

import ssm.chaincode.client.invoke.command.InvokeArgs;

public interface HasGet {
    InvokeArgs queryArgs(String value);
}
