package ssm.chaincode.dsl

import f2.dsl.cqrs.Command

actual interface SsmCommandDTO : Command {
	actual val bearerToken: String?
}
