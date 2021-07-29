package ssm.tx.client

import f2.client.F2Client
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.HTTP
import f2.client.ktor.get
import f2.client.promise
import f2.dsl.function.F2FunctionRemote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import ssm.tx.dsl.SsmApiFinderClient
import ssm.tx.dsl.features.query.*
import kotlin.js.Promise

@JsName("ssmClient")
@JsExport
fun ssmClient(host: String, port: Int, path: String? = null): Promise<SsmApiFinderClient> =
	GlobalScope.promise {
		val s2Client = F2ClientBuilder.get(HTTP, host, port, path)
		SSMFunctionClient(s2Client)
	}


actual open class SSMFunctionClient actual constructor(private val client: F2Client) : SsmApiFinderClient {

	override fun txSsmListQueryFunction(): TxSsmListQueryRemoteFunction = invokePromise { msg ->
		client.promise("txSsmListQueryFunction", msg)
	}

	override fun txSsmGetQueryFunction(): TxSsmGetQueryRemoteFunction = invokePromise { msg ->
		client.promise("txSsmGetOneQueryFunction", msg)
	}

	override fun txSsmSessionListQueryFunction(): TxSsmSessionListQueryRemoteFunction = invokePromise { msg ->
		client.promise("txSsmSessionGetListQueryFunction", msg)
	}

	override fun txSsmSessionGetQueryFunction(): TxSsmSessionLogGetQueryRemoteFunction = invokePromise { msg ->
		client.promise("txSsmSessionGetOneQueryFunction", msg)
	}

	override fun txSsmSessionLogListQueryFunction(): TxSsmSessionLogListQueryRemoteFunction = invokePromise { msg ->
		client.promise("txSsmListQueryFunction", msg)
	}

	override fun txSsmSessionLogGetQueryFunction(): TxSsmSessionLogGetQueryRemoteFunction = invokePromise { msg ->
		client.promise("txSsmSessionLogGetOneQueryFunction", msg)
	}

}

fun <E, R> invokePromise(invoke: (e: E) -> Promise<R>) = object : F2FunctionRemote<E, R> {
	override fun invoke(cmd: E): Promise<R> = invoke(cmd)
}
