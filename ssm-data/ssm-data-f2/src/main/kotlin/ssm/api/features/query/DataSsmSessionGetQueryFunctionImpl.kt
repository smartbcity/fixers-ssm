package ssm.api.features.query

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import ssm.api.extentions.toDataSession
import ssm.chaincode.dsl.query.SsmGetSessionQuery
import ssm.chaincode.dsl.query.SsmGetSessionQueryFunction
import ssm.chaincode.f2.query.SsmGetSessionQueryFunctionImpl
import ssm.data.dsl.config.DataSsmConfig
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryResult

class DataSsmSessionGetQueryFunctionImpl(
	private val config: DataSsmConfig,
	private val ssmGetSessionQueryFunction: SsmGetSessionQueryFunction
	= SsmGetSessionQueryFunctionImpl().ssmGetSessionQueryFunction(config.chaincode)
) {
	fun dataSsmSessionGetQueryFunction(): DataSsmSessionGetQueryFunction =
		f2Function { query ->
			try {
				ssmGetSessionQueryFunction.invoke(
					SsmGetSessionQuery(
						name = query.sessionId,
//						bearerToken = query.bearerToken
					)
				)
					.session?.toDataSession(config, query.ssm, query.bearerToken)
					.let { DataSsmSessionGetQueryResult(it) }
			} catch (e: Exception) {
				e.printStackTrace()
				DataSsmSessionGetQueryResult(null)
			}
		}
}
