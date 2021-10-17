package ssm.api.features.query

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import ssm.api.extentions.toDataSsm
import ssm.chaincode.dsl.model.uri.burstSsmUri
import ssm.chaincode.dsl.query.SsmGetQuery
import ssm.chaincode.f2.query.SsmGetQueryFunctionImpl
import ssm.data.dsl.config.DataSsmConfig
import ssm.data.dsl.features.query.DataSsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmGetQueryResult

class DataSsmGetQueryFunctionImpl(
	config: DataSsmConfig
) {
	private val function = SsmGetQueryFunctionImpl().ssmGetQueryFunction(config.chaincode)

	fun dataSsmGetQueryFunction(): DataSsmGetQueryFunction = f2Function { query ->
		val burst = query.ssm.burstSsmUri()!!
		function.invoke(SsmGetQuery(
			name = burst.ssmName,
//			bearerToken = query.bearerToken
		))
			.ssm
			?.toDataSsm(query.ssm)
			.let(::DataSsmGetQueryResult)
	}
}
