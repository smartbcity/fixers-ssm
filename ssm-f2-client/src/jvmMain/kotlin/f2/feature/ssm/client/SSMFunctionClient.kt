package f2.feature.ssm.client

import f2.client.F2Client
import f2.client.executeInvoke
import ssm.dsl.SSMRemoteFunction
import ssm.dsl.command.*

actual open class SSMFunctionClient actual constructor(val client: F2Client) : SSMRemoteFunction {
	override fun perform() = object : SsmPerformRemoteFunction {
		override suspend fun invoke(cmd: SsmPerformCommand): SsmPerformResult = client.executeInvoke("perform" ,cmd)
	}

	override fun start() = object : SsmStartRemoteFunction {
		override suspend fun invoke(cmd: SsmStartCommand): SsmStartResult = client.executeInvoke("start" ,cmd)
	}

	override fun init() = object : SsmCreateRemoteFunction {
		override suspend fun invoke(cmd: SsmCreateCommand): SsmCreateResult = client.executeInvoke("start" ,cmd)
	}
}