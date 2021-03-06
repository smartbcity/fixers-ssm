package ssm.client.invoke.command;

import com.google.common.collect.ImmutableMap;
import ssm.client.crypto.Sha256RSASigner;
import ssm.client.domain.Signer;
import org.junit.jupiter.api.Test;
import ssm.dsl.SsmSession;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StartCommandTest {

    @Test
    public void test_execute() throws Exception {

        //    "ssm":"Car dealership",
        //    "session":"deal20181201",
        //    "public":"Used car for 100 dollars.",
        //    "roles":{
        //      "chuck":"Buyer",
        //       "sarah":"Seller"
        //    }
        Map<String, String> roles = ImmutableMap.of("chuck", "Buyer", "sarah","Seller");
        Signer signer = Signer.loadFromFile("adam", "command/adam");
        SsmSession session = new SsmSession("Car dealership", "deal20181201", roles,"Used car for 100 dollars.", null);

        InvokeArgs invokeArgs = new StartCommandSigner(signer, session).invoke();
        invokeArgs.getArgs().forEach(System.out::println);

        String expectedJson = "{\"ssm\":\"Car dealership\",\"session\":\"deal20181201\",\"roles\":{\"chuck\":\"Buyer\",\"sarah\":\"Seller\"},\"public\":\"Used car for 100 dollars.\"}";
        assertThat(invokeArgs.getFcn()).isEqualTo("start");
        assertThat(invokeArgs.getArgs())
                .isNotEmpty()
                .containsExactly(
                        expectedJson,
                        "adam",
                        Sha256RSASigner.rsaSignAsB64(expectedJson, signer.getPair().getPrivate())
                );

    }

}