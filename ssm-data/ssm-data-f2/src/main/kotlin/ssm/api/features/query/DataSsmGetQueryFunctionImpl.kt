package ssm.api.features.query

import f2.dsl.fnc.invokeWith
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ssm.api.extentions.toDataSsm
import ssm.chaincode.dsl.model.uri.burstSsmUri
import ssm.chaincode.dsl.query.SsmGetQuery
import ssm.chaincode.dsl.query.SsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmGetQueryDTO
import ssm.data.dsl.features.query.DataSsmGetQueryFunction
import ssm.data.dsl.features.query.DataSsmGetQueryResult
import ssm.data.dsl.features.query.DataSsmGetQueryResultDTO

class DataSsmGetQueryFunctionImpl(
	private val ssmGetQueryFunction: SsmGetQueryFunction
) : DataSsmGetQueryFunction {

	override suspend fun invoke(msg: Flow<DataSsmGetQueryDTO>): Flow<DataSsmGetQueryResultDTO> =
		msg.map { payload ->
			val burst = payload.ssm.burstSsmUri()!!
			SsmGetQuery(
				name = burst.ssmName,
			).invokeWith(ssmGetQueryFunction)
				.item
				?.toDataSsm(payload.ssm)
				.let(::DataSsmGetQueryResult)

		}
}
