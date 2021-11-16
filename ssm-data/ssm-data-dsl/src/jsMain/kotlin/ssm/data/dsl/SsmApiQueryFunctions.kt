package ssm.data.dsl

import ssm.data.dsl.features.query.DataSsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction

@JsExport
@JsName("SsmApiFinder")
actual external interface SsmApiQueryFunctions {
	@JsName("dataSsmListQueryFunction")
	actual fun dataSsmListQueryFunction(): DataSsmListQueryFunction

	@JsName("dataSsmGetQueryFunction")
	actual fun dataSsmGetQueryFunction(): DataSsmGetQueryFunction

	@JsName("dataSsmSessionListQueryFunction")
	actual fun dataSsmSessionListQueryFunction(): DataSsmSessionListQueryFunction

	@JsName("dataSsmSessionGetQueryFunction")
	actual fun dataSsmSessionGetQueryFunction(): DataSsmSessionGetQueryFunction

	@JsName("dataSsmSessionLogListQueryFunction")
	actual fun dataSsmSessionLogListQueryFunction(): DataSsmSessionLogListQueryFunction

	@JsName("dataSsmSessionLogGetQueryFunction")
	actual fun dataSsmSessionLogGetQueryFunction(): DataSsmSessionLogGetQueryFunction
}
