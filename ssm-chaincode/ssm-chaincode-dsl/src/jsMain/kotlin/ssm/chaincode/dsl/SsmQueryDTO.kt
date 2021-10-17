package ssm.chaincode.dsl

import f2.dsl.cqrs.Query

@JsExport
@JsName("SsmCommandDTO")
actual external interface SsmQueryDTO : Query {
	actual val bearerToken: String?
}
