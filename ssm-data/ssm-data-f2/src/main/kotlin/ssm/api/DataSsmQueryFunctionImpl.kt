package ssm.api

import ssm.api.features.query.DataSsmGetQueryFunctionImpl
import ssm.api.features.query.DataSsmListQueryFunctionImp
import ssm.api.features.query.DataSsmSessionGetQueryFunctionImpl
import ssm.api.features.query.DataSsmSessionListQueryFunctionImpl
import ssm.api.features.query.DataSsmSessionLogGetQueryFunctionImpl
import ssm.api.features.query.DataSsmSessionLogListQueryFunctionImpl
import ssm.data.dsl.config.SsmDataConfig
import ssm.data.dsl.features.query.DataSsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction

class DataSsmQueryFunctionImpl : ssm.data.dsl.SsmApiQueryFunctions {

	override fun dataSsmListQueryFunction(config: SsmDataConfig): DataSsmListQueryFunction =
		DataSsmListQueryFunctionImp(config).dataSsmListQueryFunction()

	override fun dataSsmGetQueryFunction(config: SsmDataConfig): DataSsmGetQueryFunction =
		DataSsmGetQueryFunctionImpl(config).dataSsmGetQueryFunction()

	override fun dataSsmSessionListQueryFunction(config: SsmDataConfig): DataSsmSessionListQueryFunction =
		DataSsmSessionListQueryFunctionImpl(config).dataSsmSessionListQueryFunction()

	override fun dataSsmSessionGetQueryFunction(config: SsmDataConfig): DataSsmSessionGetQueryFunction =
		DataSsmSessionGetQueryFunctionImpl(config).dataSsmSessionGetQueryFunction()

	override fun dataSsmSessionLogListQueryFunction(config: SsmDataConfig): DataSsmSessionLogListQueryFunction =
		DataSsmSessionLogListQueryFunctionImpl(config).dataSsmSessionLogListQueryFunction()

	override fun dataSsmSessionLogGetQueryFunction(config: SsmDataConfig): DataSsmSessionLogGetQueryFunction =
		DataSsmSessionLogGetQueryFunctionImpl(config).dataSsmSessionLogGetQueryFunction()



}
