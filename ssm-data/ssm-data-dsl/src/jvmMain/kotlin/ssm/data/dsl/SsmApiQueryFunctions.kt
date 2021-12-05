package ssm.data.dsl

import ssm.data.dsl.features.query.DataChaincodeListQueryFunction
import ssm.data.dsl.features.query.DataSsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionLogListQueryFunction

actual interface SsmApiQueryFunctions {
	actual fun dataChaincodeListQueryFunction(): DataChaincodeListQueryFunction
	actual fun dataSsmListQueryFunction(): DataSsmListQueryFunction
	actual fun dataSsmGetQueryFunction(): DataSsmGetQueryFunction
	actual fun dataSsmSessionListQueryFunction(): DataSsmSessionListQueryFunction
	actual fun dataSsmSessionGetQueryFunction(): DataSsmSessionGetQueryFunction
	actual fun dataSsmSessionLogListQueryFunction(): DataSsmSessionLogListQueryFunction
	actual fun dataSsmSessionLogGetQueryFunction(): DataSsmSessionLogGetQueryFunction
//	actual fun dataUserListQueryFunction(): DataUserListQueryFunction
//	actual fun dataAdminListQueryFunction(): DataAdminListQueryFunction
}
