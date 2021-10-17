package ssm.sdk.client.command

import java.util.function.Consumer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.sdk.client.model.buildArgs
import ssm.chaincode.dsl.model.SsmSession
import ssm.sdk.sign.crypto.Sha256RSASigner.rsaSignAsB64
import ssm.sdk.sign.model.Signer.Companion.loadFromFile

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
		val roles: Map<String, String> = mapOf("chuck" to "Buyer", "sarah" to "Seller")
		val signer = loadFromFile("adam", "command/adam")
		val session = SsmSession(
			"Car dealership",
			"deal20181201",
			roles,
			"Used car for 100 dollars.",
			null
		)
		val (fcn, args) = StartCmdSigner(session).invoke(signer).buildArgs()
		args.forEach(Consumer { s: String? -> println(s) })
		val expectedJson =
			"{\"ssm\":\"Car dealership\",\"session\":\"deal20181201\",\"roles\":" +
					"{\"chuck\":\"Buyer\",\"sarah\":\"Seller\"},\"public\":\"Used car for 100 dollars.\"}"
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
