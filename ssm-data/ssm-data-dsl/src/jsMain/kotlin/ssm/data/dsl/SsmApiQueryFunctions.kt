package ssm.data.dsl

import ssm.data.dsl.config.SsmDataConfig
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
	actual fun dataSsmListQueryFunction(config: SsmDataConfig): DataSsmListQueryFunction

	@JsName("dataSsmGetQueryFunction")
	actual fun dataSsmGetQueryFunction(config: SsmDataConfig): DataSsmGetQueryFunction

	@JsName("dataSsmSessionListQueryFunction")
	actual fun dataSsmSessionListQueryFunction(config: SsmDataConfig): DataSsmSessionListQueryFunction

	@JsName("dataSsmSessionGetQueryFunction")
	actual fun dataSsmSessionGetQueryFunction(config: SsmDataConfig): DataSsmSessionGetQueryFunction

	@JsName("dataSsmSessionLogListQueryFunction")
	actual fun dataSsmSessionLogListQueryFunction(config: SsmDataConfig): DataSsmSessionLogListQueryFunction

	@JsName("dataSsmSessionLogGetQueryFunction")
	actual fun dataSsmSessionLogGetQueryFunction(config: SsmDataConfig): DataSsmSessionLogGetQueryFunction
}
