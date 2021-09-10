// package ssm.tx.client
// 
// import f2.client.F2Client
// import f2.client.executeInvoke
// import f2.dsl.fnc.F2FunctionRemote
// import ssm.tx.dsl.SsmApiFinderClient
// import ssm.tx.dsl.features.query.*
// 
// actual open class SSMFunctionClient actual constructor(private val client: F2Client) : SsmApiFinderClient {
// 
// 	override fun txSsmListQueryFunction(): TxSsmListQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::txSsmListQueryFunction.name, cmd)
// 	}
// 
// 	override fun txSsmGetQueryFunction(): TxSsmGetQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::txSsmGetQueryFunction.name, cmd)
// 	}
// 
// 	override fun txSsmSessionListQueryFunction(): TxSsmSessionListQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::txSsmSessionListQueryFunction.name, cmd)
// 	}
// 
// 	override fun txSsmSessionGetQueryFunction(): TxSsmSessionLogGetQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::txSsmSessionGetQueryFunction.name, cmd)
// 	}
// 
// 	override fun txSsmSessionLogListQueryFunction(): TxSsmSessionLogListQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::txSsmSessionLogListQueryFunction.name, cmd)
// 	}
// 
// 	override fun txSsmSessionLogGetQueryFunction(): TxSsmSessionLogGetQueryRemoteFunction = invokeSuspend { cmd ->
// 		client.executeInvoke(SSMFunctionClient::txSsmSessionLogGetQueryFunction.name, cmd)
// 	}
// }
// 
// fun  <E, R>  invokeSuspend(invoke: suspend (e: E) -> R) = object : F2FunctionRemote<E, R> {
// 	override suspend fun invoke(cmd: E): R = invoke(cmd)
// }
