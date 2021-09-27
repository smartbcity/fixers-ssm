package ssm.chaincode.dsl

import f2.dsl.cqrs.Command

@JsExport
@JsName("SsmCommandDTO")
actual external interface SsmCommandDTO : Command {
	actual val bearerToken: String?
}
