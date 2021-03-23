package ssm.dsl.function

import ssm.dsl.command.*
import ssm.dsl.query.*

interface SsmAdminFunction {
	fun start(): SsmStartFunction
	fun create(): SsmCreateFunction
}

interface SSMAdminRemoteFunction {
	fun start(): SsmStartRemoteFunction
	fun create(): SsmCreateRemoteFunction
}