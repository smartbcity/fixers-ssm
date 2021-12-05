package ssm.api.features.query

import f2.dsl.fnc.invoke
import f2.dsl.fnc.invokeWith
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import ssm.api.extentions.toDataSsm
import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.couchdb.dsl.query.CouchdbChaincodeListQuery
import ssm.couchdb.dsl.query.CouchdbChaincodeListQueryFunction
import ssm.couchdb.dsl.query.CouchdbSsmListQuery
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction
import ssm.data.dsl.features.query.DataChaincodeListQuery
import ssm.data.dsl.features.query.DataChaincodeListQueryFunction
import ssm.data.dsl.features.query.DataChaincodeListQueryResult
import ssm.data.dsl.features.query.DataSsmListQuery
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryResult
import ssm.data.dsl.model.DataSsm

class DataChaincodeListQueryFunctionImp(
	private val couchdbChaincodeListQueryFunction: CouchdbChaincodeListQueryFunction
): DataChaincodeListQueryFunction {

	override suspend fun invoke(msg: Flow<DataChaincodeListQuery>): Flow<DataChaincodeListQueryResult> =
		msg.map {
			couchdbChaincodeListQueryFunction.invoke(CouchdbChaincodeListQuery()).let {
				DataChaincodeListQueryResult(it.items)
			}
		}
}
