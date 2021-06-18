package ssm.chaincode.f2.client

import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.Protocol
import f2.client.ktor.get
import f2.client.F2Client
import f2.client.promise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
//import ssm.dsl.command.*
import kotlin.js.Promise

@JsName("ssmClient")
@JsExport
fun ssmClient(protocol: Protocol, host: String, port: Int, path: String? = null): Promise<SSMFunctionClient> =
	GlobalScope.promise {
		val s2Client = F2ClientBuilder.get(protocol, host, port, path)
		SSMFunctionClient(s2Client)
	}

actual open class SSMFunctionClient actual constructor(val client: F2Client) : SSMRemoteFunction {

//	override fun perform() = object : SsmPerformRemoteFunction {
//		override fun invoke(cmd: SsmPerformCommand): Promise<SsmPerformResult> = client.promise("perform", cmd)
//	}

}