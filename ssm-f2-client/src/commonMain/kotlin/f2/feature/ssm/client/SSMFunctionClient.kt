package f2.feature.ssm.client

import f2.client.F2Client
import ssm.dsl.command.SsmPerformRemoteFunction

interface SSMRemoteFunction {
	fun perform(): SsmPerformRemoteFunction
}


expect open class SSMFunctionClient(client: F2Client) : SSMRemoteFunction