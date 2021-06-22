package ssm.chaincode.client.invoke.command

import com.google.common.collect.ImmutableMap
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.chaincode.client.invoke.command.StartCommandSigner
import ssm.chaincode.dsl.SsmSessionBase
import ssm.sdk.sign.crypto.Sha256RSASigner.Companion.rsaSignAsB64
import ssm.sdk.sign.model.Signer.Companion.loadFromFile
import java.util.function.Consumer

class StartCommandTest {
    @Test
    @Throws(Exception::class)
    fun test_execute() {

        //    "ssm":"Car dealership",
        //    "session":"deal20181201",
        //    "public":"Used car for 100 dollars.",
        //    "roles":{
        //      "chuck":"Buyer",
        //       "sarah":"Seller"
        //    }
        val roles: Map<String, String> = ImmutableMap.of("chuck", "Buyer", "sarah", "Seller")
        val signer = loadFromFile("adam", "command/adam")
        val session = SsmSessionBase("Car dealership", "deal20181201", roles, "Used car for 100 dollars.", null)
        val (fcn, args) = StartCommandSigner(signer, session).invoke()
        args.forEach(Consumer { s: String? -> println(s) })
        val expectedJson =
            "{\"ssm\":\"Car dealership\",\"session\":\"deal20181201\",\"roles\":{\"chuck\":\"Buyer\",\"sarah\":\"Seller\"},\"public\":\"Used car for 100 dollars.\"}"
        Assertions.assertThat(fcn).isEqualTo("start")
        Assertions.assertThat(args)
            .isNotEmpty
            .containsExactly(
                expectedJson,
                "adam",
                rsaSignAsB64(expectedJson, signer.pair.private)
            )
    }
}