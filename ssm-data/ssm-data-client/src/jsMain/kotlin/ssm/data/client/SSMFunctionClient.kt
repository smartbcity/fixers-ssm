//  package ssm.data.client
// 
// import f2.client.F2Client
// import f2.client.ktor.F2ClientBuilder
// import f2.client.ktor.HTTP
// import f2.client.ktor.get
// import f2.client.promise
// import kotlinx.coroutines.GlobalScope
// import kotlinx.coroutines.promise
// import ssm.data.dsl.SsmApiFinderClient
// import ssm.data.dsl.features.query.*
// import kotlin.js.Promise
// 
// @JsName("ssmClient")
// @JsExport
// fun ssmClient(host: String, port: Int, path: String? = null): Promise<SsmApiFinderClient> =
// 	GlobalScope.promise {
// 		val s2Client = F2ClientBuilder.get(HTTP, host, port, path)
// 		SSMFunctionClient(s2Client)
// 	}
// 
// 
// actual open class SSMFunctionClient actual constructor(private val client: F2Client) : SsmApiFinderClient {
// 
// 	override fun dataSsmListQueryFunction(): DataSsmListQueryRemoteFunction = invokePromise { msg ->
// 		client.promise("dataSsmListQueryFunction", msg)
// 	}
// 
// 	override fun dataSsmGetQueryFunction(): DataSsmGetQueryRemoteFunction = invokePromise { msg ->
// 		client.promise("dataSsmGetOneQueryFunction", msg)
// 	}
// 
// 	override fun dataSsmSessionListQueryFunction(): DataSsmSessionListQueryRemoteFunction = invokePromise { msg ->
// 		client.promise("dataSsmSessionGetListQueryFunction", msg)
// 	}
// 
// 	override fun dataSsmSessionGetQueryFunction(): DataSsmSessionLogGetQueryRemoteFunction = invokePromise { msg ->
// 		client.promise("dataSsmSessionGetOneQueryFunction", msg)
// 	}
// 
// 	override fun dataSsmSessionLogListQueryFunction(): DataSsmSessionLogListQueryRemoteFunction = invokePromise { msg ->
// 		client.promise("dataSsmListQueryFunction", msg)
// 	}
// 
// 	override fun dataSsmSessionLogGetQueryFunction(): DataSsmSessionLogGetQueryRemoteFunction = invokePromise { msg ->
// 		client.promise("dataSsmSessionLogGetOneQueryFunction", msg)
// 	}
// 
// }
// 
// fun <E, R> invokePromise(invoke: (e: E) -> Promise<R>) = object : F2FunctionRemote<E, R> {
// 	override fun invoke(cmd: E): Promise<R> = invoke(cmd)
// }
