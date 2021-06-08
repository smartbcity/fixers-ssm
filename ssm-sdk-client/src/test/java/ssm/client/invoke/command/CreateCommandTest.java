package ssm.client.invoke.command;

import ssm.client.crypto.Sha256RSASigner;
import ssm.client.domain.Signer;
import ssm.client.crypto.KeyPairReader;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import ssm.dsl.SsmBase;
import ssm.dsl.SsmTransitionBase;

import java.security.KeyPair;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateCommandTest {

    /**
     * "name":"Car dealership",
     * "transitions":[
     * {
     * "from":0,
     * "to":1,
     * "role":"Seller",
     * "action":"Sell"
     * },
     * {
     * "from":1,
     * "to":2,
     * "role":"Buyer",
     * "action":"Buy"
     * }
     * ]
     */
    @Test
    public void testExecute() throws Exception {
        KeyPair adamPair = KeyPairReader.loadKeyPair("command/adam");

        Signer signer = new Signer("adam", adamPair);
        SsmTransitionBase sell = new SsmTransitionBase(0, 1, "Seller", "Sell");
        SsmTransitionBase buy = new SsmTransitionBase(1, 2, "Buyer", "Buy");

        SsmBase ssm = new SsmBase("dealership", Lists.newArrayList(sell, buy).toArray(new SsmTransitionBase[2]));


        InvokeArgs invokeArgs = new CreateCommandSigner(signer, ssm).invoke();

        invokeArgs.getArgs().forEach(System.out::println);

        /**
         *{
         *    "InvokeArgs": [
         *       "create",
         *        "{\"name\":\"Car dealership\",\"transitions\":[{\"from\":0,\"to\":1,\"role\":\"Seller\",\"action\":\"Sell\"},{\"from\":1,\"to\":2,\"role\":\"Buyer\",\"action\":\"Buy\"}]}",
         *        "adam",
         *        "HUYPNHkgCfB+yr7TeYpi1dcU8me+MzPqFxtxJWBeIunBo/KHuG7/bS32MakwwDf7ehyIWDuXF42b/IT9RofKLU6P5DwpadDxE6cj1qlcIgRd1K015D9wvKFdJW9SfYTJhINwuitFhus/eNLcGb+CdyoyD0GRrYRONJ8C6/Hop2PwyCZ6v5aya+XxEoh+2EjPkdeDn0VbdXR5wGP7emI4R9ZhAHwp3ebHV139OdSvvGobllN9hUZdKBkF2nYinti/YfrBI9mfY4svPCg1zZfK0hfegAa8Rekysno/2+d9jkJMwCveTzclMpSFGlVO3mRr4yWQOIEre7VpaxfGx8zdow=="
         *    ]
         *}
         */

        String expectedJson = "{\"name\":\"dealership\",\"transitions\":[{\"from\":0,\"to\":1,\"role\":\"Seller\",\"action\":\"Sell\"},{\"from\":1,\"to\":2,\"role\":\"Buyer\",\"action\":\"Buy\"}]}";

        assertThat(invokeArgs.getFcn()).isEqualTo("create");
        assertThat(invokeArgs.getArgs())
                .isNotEmpty()
                .containsExactly(
                        expectedJson,
                        "adam",
                        Sha256RSASigner.rsaSignAsB64(expectedJson, signer.getPair().getPrivate())
                );

    }
}