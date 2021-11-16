package ssm.api.features.query

import f2.dsl.fnc.invokeWith
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ssm.api.features.query.internal.DataSsmSessionConvertFunctionImpl
import ssm.api.features.query.internal.DataSsmSessionConvertQuery
import ssm.chaincode.dsl.model.SsmSessionState
import ssm.chaincode.dsl.model.uri.ChaincodeUriBurst
import ssm.chaincode.dsl.model.uri.burstSsmUri
import ssm.chaincode.dsl.model.uri.compact
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQuery
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryDTO
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryResult
import ssm.data.dsl.features.query.DataSsmSessionListQueryResultDTO

class DataSsmSessionListQueryFunctionImpl(
	private val dataSsmSessionConvertFunctionImpl: DataSsmSessionConvertFunctionImpl,
	private val couchdbSsmSessionStateListQueryFunction: CouchdbSsmSessionStateListQueryFunction,
) : DataSsmSessionListQueryFunction {

	override suspend fun invoke(msg: Flow<DataSsmSessionListQueryDTO>): Flow<DataSsmSessionListQueryResultDTO> =
		msg.map { payload ->
			val burst = payload.ssm.burstSsmUri()!!
			CouchdbSsmSessionStateListQuery(
				chaincodeUri = ChaincodeUriBurst(
					channelId = burst.channelId,
					chaincodeId = burst.chaincodeId,
				).compact(),
				ssm = burst.ssmName,
				pagination = null
			).invokeWith(couchdbSsmSessionStateListQueryFunction)
				.page.list
				.filter { sessionState -> sessionState.session.isNotBlank() }
				.map { sessionState ->
					DataSsmSessionConvertQuery(
						sessionState = sessionState as SsmSessionState,
						ssmUri = payload.ssm
					).invokeWith(dataSsmSessionConvertFunctionImpl)
				}.let {
					DataSsmSessionListQueryResult(it)
				}
		}
}
