package ssm.sdk.client.invoke.command

import java.util.function.Consumer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import ssm.sdk.client.extention.loadFromFile
import ssm.sdk.client.model.buildArgs
import ssm.sdk.sign.model.SignerUser

class RegisterCommandTest {
	@Test
	@Throws(Exception::class)
	fun test_execute() {
		val signer = SignerUser.loadFromFile("adam", "command/adam")
		val agent = loadFromFile("vivi", "command/vivi")
		val (fcn, args) = RegisterCmd(agent).invoke(signer).buildArgs()
		args.forEach(Consumer { s: String? -> println(s) })
		Assertions.assertThat(fcn).isEqualTo("register")
		Assertions.assertThat(args)
			.isNotEmpty
			.containsExactly(
				"{\"name\":\"vivi\",\"pub\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs2oFqOlrdpz/fEi5rQfEFWeWTeSXSLaaEwQAYof+EIQTYlvQ+1uk//dBFn+bPcp+BSdzgkra4jd0qsImVMgnrWIDUhs3vl2Wi9TgAQHXT/DtIbvlj+ZdPFTUzd3vb+8NR4i4ha8Yg9bbd5noaf3f40aJ1CY+huRV0/ElOFI5/hM00rZdxiFNcQ9NiA++osUzb4OZ5TqnePmwDpnI7qbE9mTOlbJju9JfmnppZv2HRkWRsdCPjKm+mKv5O9xR+Np5bSMTGqrVH0eyMleHrALEojDdfLt2FTf+ZiCCVKulV5jbMpKf3Qt7891vC5/QyDrtbEz7aPhU4FT1W2ks6rOLcwIDAQAB\"}",
				"adam",
				"oRz0NP6XnLLC6TnRZUDzHKPVlvDmgclArV1jknd6Z57mdnBAfStsxsLZmPg/+oOIXNX0sJQSh1bE1CyjJ4JQqNtYkEB9cNwnTJ26aGwMlNtnVLcWv80GxnMr0vso5W4CrX6Tv/lt/FW3dKShmVYjR0X8e96cFnZofbV4fEHsECDlgTKP242xhbz0n/tGAInlGnd8NFJBKfHEi7dEYZjUEbQDuRmMnWE12p9VbsS7EQHuxZx3vieqas9W1cPmiJZrYDDmQXUpzI5ucJ4VcJkuCJ54WWSF4XBXLp5yx3GxLjqGdPlymQ3cWiLpEfJZguSNwRa53p4hTyFU3HVyiCzCsw=="
			)
	}
}
