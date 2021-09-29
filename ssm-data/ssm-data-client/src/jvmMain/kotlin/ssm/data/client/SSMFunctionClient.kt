// package ssm.data.client
// 
// import f2.client.F2Client
// import f2.client.executeInvoke
// import f2.dsl.fnc.F2FunctionRemote
// import ssm.data.dsl.SsmApiFinderClient
// import ssm.data.dsl.features.query.*
// 
// actual open class SSMFunctionClient actual constructor(private val client: F2Client) : SsmApiFinderClient {
// 
// 	override fun dataSsmListQueryFunction(): DataSsmListQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::dataSsmListQueryFunction.name, cmd)
// 	}
// 
// 	override fun dataSsmGetQueryFunction(): DataSsmGetQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::dataSsmGetQueryFunction.name, cmd)
// 	}
// 
// 	override fun dataSsmSessionListQueryFunction(): DataSsmSessionListQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::dataSsmSessionListQueryFunction.name, cmd)
// 	}
// 
// 	override fun dataSsmSessionGetQueryFunction(): DataSsmSessionLogGetQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::dataSsmSessionGetQueryFunction.name, cmd)
// 	}
// 
// 	override fun dataSsmSessionLogListQueryFunction(): DataSsmSessionLogListQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::dataSsmSessionLogListQueryFunction.name, cmd)
// 	}
// 
// 	override fun dataSsmSessionLogGetQueryFunction(): DataSsmSessionLogGetQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::dataSsmSessionLogGetQueryFunction.name, cmd)
// 	}
// }
// 
// fun  <E, R>  invokeSuspend(invoke: suspend (e: E) -> R) = object : F2FunctionRemote<E, R> {
// 	override suspend fun invoke(cmd: E): R = invoke(cmd)
// }
