package ssm.dsl.function

import ssm.dsl.command.*
import ssm.dsl.query.*

interface SSMFunction {
	fun perform(): SsmPerformFunction
}

interface SSMRemoteFunction {
	fun perform(): SsmPerformRemoteFunction
}