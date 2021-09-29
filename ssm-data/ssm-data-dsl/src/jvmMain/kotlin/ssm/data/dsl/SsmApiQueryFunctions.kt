package ssm.data.dsl

import ssm.data.dsl.config.DataSsmConfig
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction

actual interface SsmApiQueryFunctions {
	actual fun dataSsmListQueryFunction(config: DataSsmConfig): DataSsmListQueryFunction
	actual fun dataSsmGetQueryFunction(config: DataSsmConfig): ssm.data.dsl.features.query.DataSsmGetQueryFunction
	actual fun dataSsmSessionListQueryFunction(config: DataSsmConfig): DataSsmSessionListQueryFunction
	actual fun dataSsmSessionGetQueryFunction(config: DataSsmConfig): DataSsmSessionGetQueryFunction
	actual fun dataSsmSessionLogListQueryFunction(config: DataSsmConfig): DataSsmSessionLogListQueryFunction
	actual fun dataSsmSessionLogGetQueryFunction(config: DataSsmConfig): DataSsmSessionLogGetQueryFunction
}
