package ssm.data.dsl

import ssm.data.dsl.config.DataSsmConfig
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction

@JsExport
@JsName("SsmApiFinder")
actual external interface SsmApiQueryFunctions {
	@JsName("dataSsmListQueryFunction")
	actual fun dataSsmListQueryFunction(config: DataSsmConfig): DataSsmListQueryFunction

	@JsName("dataSsmGetQueryFunction")
	actual fun dataSsmGetQueryFunction(config: DataSsmConfig): ssm.data.dsl.features.query.DataSsmGetQueryFunction

	@JsName("dataSsmSessionListQueryFunction")
	actual fun dataSsmSessionListQueryFunction(config: DataSsmConfig): DataSsmSessionListQueryFunction

	@JsName("dataSsmSessionGetQueryFunction")
	actual fun dataSsmSessionGetQueryFunction(config: DataSsmConfig): DataSsmSessionGetQueryFunction

	@JsName("dataSsmSessionLogListQueryFunction")
	actual fun dataSsmSessionLogListQueryFunction(config: DataSsmConfig): DataSsmSessionLogListQueryFunction

	@JsName("dataSsmSessionLogGetQueryFunction")
	actual fun dataSsmSessionLogGetQueryFunction(config: DataSsmConfig): DataSsmSessionLogGetQueryFunction
}
