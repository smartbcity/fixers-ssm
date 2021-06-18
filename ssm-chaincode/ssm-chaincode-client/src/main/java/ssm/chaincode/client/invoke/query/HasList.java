package ssm.chaincode.client.invoke.query;

import ssm.chaincode.client.invoke.command.InvokeArgs;

public interface HasList {

    String LIST_FUNCTION = "list";

    InvokeArgs listArgs();
}
