package f2.feature.ssm.client

import f2.client.F2Client
import f2.client.executeInvoke
import ssm.dsl.function.SSMRemoteFunction
import ssm.dsl.command.*

actual open class SSMFunctionClient actual constructor(val client: F2Client) : SSMRemoteFunction {

	override fun perform() = object : SsmPerformRemoteFunction {
		override suspend fun invoke(cmd: SsmPerformCommand): SsmPerformResult = client.executeInvoke("perform" ,cmd)
	}

}