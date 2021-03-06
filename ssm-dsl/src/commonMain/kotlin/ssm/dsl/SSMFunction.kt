package ssm.dsl

import ssm.dsl.command.*
import ssm.dsl.query.GetSsmFunction
import ssm.dsl.query.GetSsmSessionFunction

interface SSMFunction {
	fun perform(): SsmPerformFunction
	fun start(): SsmStartFunction
	fun create(): SsmCreateFunction

	fun getSsm(): GetSsmFunction
	fun getSsmSession(): GetSsmSessionFunction
}

interface SSMRemoteFunction {
	fun perform(): SsmPerformRemoteFunction
	fun start(): SsmStartRemoteFunction
	fun init(): SsmCreateRemoteFunction
}