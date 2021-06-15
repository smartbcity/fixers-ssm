package x2.ssm.client

import GetSsmListQueryRemoteFunction
import GetSsmSessionListQueryRemoteFunction
import GetSsmSessionQueryRemoteFunction
import f2.client.F2Client
import f2.client.executeInvoke
import f2.dsl.function.F2FunctionRemote
import ssm.chaincode.dsl.SsmSessionStateLog
import ssm.chaincode.dsl.query.SsmGetQuery
import ssm.chaincode.dsl.query.SsmGetQueryRemoteFunction
import ssm.chaincode.dsl.query.SsmGetResult
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
import x2.api.ssm.domain.SsmApiFinder

actual open class SSMFunctionClient actual constructor(private val client: F2Client) : SsmApiFinder {
	override fun getAllSsm(): GetSsmListQueryRemoteFunction = invokeSuspend { cmd ->
		client.executeInvoke("getAllSsm", cmd)
	}

	override fun getSsm(): SsmGetQueryRemoteFunction = invokeSuspend<SsmGetQuery, SsmGetResult> { cmd ->
		client.executeInvoke("getSsm", cmd)
	}

	override fun getAllSessions(): GetSsmSessionListQueryRemoteFunction = invokeSuspend { cmd ->
		client.executeInvoke("getAllSessions", cmd)
	}

	override fun getSession(): GetSsmSessionQueryRemoteFunction = invokeSuspend { cmd ->
		client.executeInvoke("getSession", cmd)
	}

	override fun getSessionLogs(): F2FunctionRemote<SsmGetSessionLogsQuery, Array<SsmSessionStateLog>> = invokeSuspend { cmd ->
		client.executeInvoke("getSessionLogs", cmd)
	}
}

fun  <E, R>  invokeSuspend(invoke: suspend (e: E) -> R) = object : F2FunctionRemote<E, R> {
	override suspend fun invoke(cmd: E): R = invoke(cmd)
}
