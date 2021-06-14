package ssm.chaincode.client.invoke.command;

import java.util.Base64;

import ssm.sdk.sign.crypto.Sha256RSASigner;
import ssm.sdk.json.JsonUtils;
import ssm.sdk.sign.model.Signer;

public abstract class CommandSigner<T> {

    private final Signer signer;
    private final String command;
    private final T value;

    public CommandSigner(Signer signer, String command, T value) {
        this.signer = signer;
        this.command = command;
        this.value = value;
    }

    public InvokeArgs invoke() throws Exception {
        String json = JsonUtils.Companion.toJson(value);
        String toSign = valueToSign(json);
        byte[] signature = Sha256RSASigner.Companion.rsaSign(toSign, signer.getPair().getPrivate());
        String b64Signature = Base64.getEncoder().encodeToString(signature);
        return buildArgs(command, json, signer.getName(), b64Signature);
    }

    public String getCommandName() {
        return command;
    }

    protected String valueToSign(String json) throws Exception {
        return json;
    }

    protected InvokeArgs buildArgs(String command, String json, String signer, String b64Signature) throws Exception {
        return new InvokeArgs(command, json, signer, b64Signature);
    }

}
