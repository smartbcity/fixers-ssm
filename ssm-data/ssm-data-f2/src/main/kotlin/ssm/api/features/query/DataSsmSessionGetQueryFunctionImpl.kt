package ssm.api.features.query

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import ssm.api.extentions.toDataSession
import ssm.chaincode.dsl.query.SsmGetSessionQuery
import ssm.chaincode.f2.query.SsmGetSessionQueryFunctionImpl
import ssm.data.dsl.config.DataSsmConfig
import ssm.data.dsl.features.query.DataSsmSessionGetQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionGetQueryResult

class DataSsmSessionGetQueryFunctionImpl(
	private val config: DataSsmConfig
) {
	fun dataSsmSessionGetQueryFunction(): DataSsmSessionGetQueryFunction =
		f2Function { cmd ->
			val sessionQuery = SsmGetSessionQuery(
				name = cmd.sessionId,
				bearerToken = cmd.bearerToken
			)

			try {
				SsmGetSessionQueryFunctionImpl().ssmGetSessionQueryFunction(config.chaincode).invoke(sessionQuery)
					.session
					?.toDataSession(config, cmd.bearerToken).let {
						DataSsmSessionGetQueryResult(
							it
						)
					}
			} catch (e: Exception) {
				e.printStackTrace()
				DataSsmSessionGetQueryResult(null)
			}
		}
}
