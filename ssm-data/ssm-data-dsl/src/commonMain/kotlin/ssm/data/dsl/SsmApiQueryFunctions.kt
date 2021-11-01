package ssm.data.dsl

import ssm.data.dsl.config.SsmDataConfig
import ssm.data.dsl.features.query.DataSsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction

/**
 * - fun dataSsmListQueryFunction(config: DataSsmConfig): [DataSsmListQueryFunction]
 * - fun dataSsmGetQueryFunction(config: DataSsmConfig): [DataSsmGetQueryFunction]
 * - fun dataSsmSessionListQueryFunction(config: DataSsmConfig): [DataSsmSessionListQueryFunction]
 * - fun dataSsmSessionGetQueryFunction(config: DataSsmConfig): [DataSsmSessionGetQueryFunction]
 * - fun dataSsmSessionLogListQueryFunction(config: DataSsmConfig): [DataSsmSessionLogListQueryFunction]
 * - fun dataSsmSessionLogGetQueryFunction(config: DataSsmConfig): [DataSsmSessionLogGetQueryFunction]
 * @d2 model
 * @title Entrypoints
 * @parent [ssm.data.dsl.SsmDataD2]
 * @title Synthesis and global objects of the API
 */
expect interface SsmApiQueryFunctions {
	fun dataSsmListQueryFunction(config: SsmDataConfig): DataSsmListQueryFunction
	fun dataSsmGetQueryFunction(config: SsmDataConfig): DataSsmGetQueryFunction
	fun dataSsmSessionListQueryFunction(config: SsmDataConfig): DataSsmSessionListQueryFunction
	fun dataSsmSessionGetQueryFunction(config: SsmDataConfig): DataSsmSessionGetQueryFunction
	fun dataSsmSessionLogListQueryFunction(config: SsmDataConfig): DataSsmSessionLogListQueryFunction
	fun dataSsmSessionLogGetQueryFunction(config: SsmDataConfig): DataSsmSessionLogGetQueryFunction
}
