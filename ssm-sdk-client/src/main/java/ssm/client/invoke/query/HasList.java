package ssm.client.invoke.query;

import ssm.client.invoke.command.InvokeArgs;

public interface HasList {

    String LIST_FUNCTION = "list";

    InvokeArgs listArgs();
}
