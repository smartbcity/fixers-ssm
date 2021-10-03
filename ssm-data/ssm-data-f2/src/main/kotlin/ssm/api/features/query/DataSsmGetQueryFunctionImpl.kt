package ssm.api.features.query

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import ssm.api.extentions.toDataSsm
import ssm.chaincode.dsl.query.SsmGetQuery
import ssm.chaincode.f2.query.SsmGetQueryFunctionImpl
import ssm.data.dsl.config.DataSsmConfig
import ssm.data.dsl.features.query.DataSsmGetQueryResult

class DataSsmGetQueryFunctionImpl(
	private val config: DataSsmConfig
) {
	val function = SsmGetQueryFunctionImpl().ssmGetQueryFunction(config.chaincode)
	fun dataSsmGetQueryFunction(): ssm.data.dsl.features.query.DataSsmGetQueryFunction = f2Function { query ->
		function.invoke(SsmGetQuery(
			name = query.ssm,
			bearerToken = query.bearerToken
		))
			.ssm
			?.toDataSsm(config)
			.let(::DataSsmGetQueryResult)
	}
}
