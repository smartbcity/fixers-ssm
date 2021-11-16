package ssm.data.dsl

import ssm.data.dsl.features.query.DataSsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction

/**
 * - fun dataSsmListQueryFunction(): [DataSsmListQueryFunction]
 * - fun dataSsmGetQueryFunction(): [DataSsmGetQueryFunction]
 * - fun dataSsmSessionListQueryFunction(): [DataSsmSessionListQueryFunction]
 * - fun dataSsmSessionGetQueryFunction(): [DataSsmSessionGetQueryFunction]
 * - fun dataSsmSessionLogListQueryFunction(): [DataSsmSessionLogListQueryFunction]
 * - fun dataSsmSessionLogGetQueryFunction(): [DataSsmSessionLogGetQueryFunction]
 * @d2 model
 * @title Entrypoints
 * @parent [ssm.data.dsl.DataSsmD2]
 * @title Synthesis and global objects of the API
 */
expect interface SsmApiQueryFunctions {
	fun dataSsmListQueryFunction(): DataSsmListQueryFunction
	fun dataSsmGetQueryFunction(): DataSsmGetQueryFunction
	fun dataSsmSessionListQueryFunction(): DataSsmSessionListQueryFunction
	fun dataSsmSessionGetQueryFunction(): DataSsmSessionGetQueryFunction
	fun dataSsmSessionLogListQueryFunction(): DataSsmSessionLogListQueryFunction
	fun dataSsmSessionLogGetQueryFunction(): DataSsmSessionLogGetQueryFunction
}
