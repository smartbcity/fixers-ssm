package ssm.chaincode.dsl

import f2.dsl.cqrs.Command

actual interface SsmCommandDTO : Command {
	actual val baseUrl: String
	actual val channelId: String?
	actual val chaincodeId: String?
	actual val bearerToken: String?
}