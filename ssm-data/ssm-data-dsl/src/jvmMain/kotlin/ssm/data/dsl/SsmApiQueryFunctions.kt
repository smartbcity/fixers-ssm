package ssm.data.dsl

import ssm.data.dsl.config.SsmDataConfig
import ssm.data.dsl.features.query.DataSsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction

actual interface SsmApiQueryFunctions {
	actual fun dataSsmListQueryFunction(config: SsmDataConfig): DataSsmListQueryFunction
	actual fun dataSsmGetQueryFunction(config: SsmDataConfig): DataSsmGetQueryFunction
	actual fun dataSsmSessionListQueryFunction(config: SsmDataConfig): DataSsmSessionListQueryFunction
	actual fun dataSsmSessionGetQueryFunction(config: SsmDataConfig): DataSsmSessionGetQueryFunction
	actual fun dataSsmSessionLogListQueryFunction(config: SsmDataConfig): DataSsmSessionLogListQueryFunction
	actual fun dataSsmSessionLogGetQueryFunction(config: SsmDataConfig): DataSsmSessionLogGetQueryFunction
}
