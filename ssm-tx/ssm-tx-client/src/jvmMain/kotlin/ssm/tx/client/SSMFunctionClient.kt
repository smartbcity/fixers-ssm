package ssm.tx.client

import f2.client.F2Client
import f2.client.executeInvoke
import f2.dsl.function.F2FunctionRemote
import ssm.tx.dsl.SsmApiFinderClient
import ssm.tx.dsl.features.query.*

actual open class SSMFunctionClient actual constructor(private val client: F2Client) : SsmApiFinderClient {

	override fun txSsmListQueryFunction(): TxSsmListQueryRemoteFunction = invokeSuspend { cmd ->
		client.executeInvoke(SSMFunctionClient::txSsmListQueryFunction.name, cmd)
	}

	override fun txSsmGetOneQueryFunction(): TxSsmGetQueryRemoteFunction = invokeSuspend { cmd ->
		client.executeInvoke(SSMFunctionClient::txSsmGetOneQueryFunction.name, cmd)
	}

	override fun txSsmSessionGetListQueryFunction(): TxSsmSessionListQueryRemoteFunction = invokeSuspend { cmd ->
		client.executeInvoke(SSMFunctionClient::txSsmSessionGetListQueryFunction.name, cmd)
	}

	override fun txSsmSessionGetOneQueryFunction(): TxSsmSessionLogGetQueryRemoteFunction = invokeSuspend { cmd ->
		client.executeInvoke(SSMFunctionClient::txSsmSessionGetOneQueryFunction.name, cmd)
	}

	override fun txSsmSessionLogGetListQueryFunction(): TxSsmSessionLogListQueryRemoteFunction = invokeSuspend { cmd ->
		client.executeInvoke(SSMFunctionClient::txSsmSessionLogGetListQueryFunction.name, cmd)
	}

	override fun txSsmSessionLogGetOneQueryFunction(): TxSsmSessionLogGetQueryRemoteFunction = invokeSuspend { cmd ->
		client.executeInvoke(SSMFunctionClient::txSsmSessionLogGetOneQueryFunction.name, cmd)
	}
}

fun  <E, R>  invokeSuspend(invoke: suspend (e: E) -> R) = object : F2FunctionRemote<E, R> {
	override suspend fun invoke(cmd: E): R = invoke(cmd)
}
