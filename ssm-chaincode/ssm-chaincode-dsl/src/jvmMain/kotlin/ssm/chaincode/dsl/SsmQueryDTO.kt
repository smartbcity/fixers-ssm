package ssm.chaincode.dsl

import f2.dsl.cqrs.Query

actual interface SsmQueryDTO : Query {
	actual val bearerToken: String?
}
