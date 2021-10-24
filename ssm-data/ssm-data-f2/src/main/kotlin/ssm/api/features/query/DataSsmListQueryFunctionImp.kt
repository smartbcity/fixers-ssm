package ssm.api.features.query

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import ssm.api.extentions.toDataSsm
import ssm.chaincode.dsl.model.uri.ChaincodeUriBurstDTO
import ssm.chaincode.dsl.model.uri.burstChaincode
import ssm.couchdb.dsl.query.CouchdbSsmListQuery
import ssm.couchdb.dsl.query.CouchdbSsmListQueryFunction
import ssm.couchdb.f2.query.CouchdbSsmListQueryFunctionImpl
import ssm.data.dsl.config.SsmDataConfig
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryResult
import ssm.data.dsl.model.DataSsm

class DataSsmListQueryFunctionImp(
	private val config: SsmDataConfig,
	private val couchdbSsmListQueryFunction: CouchdbSsmListQueryFunction =
		CouchdbSsmListQueryFunctionImpl(config.couchdb).couchdbSsmListQueryFunction(),
) {
	fun dataSsmListQueryFunction(): DataSsmListQueryFunction = f2Function { query ->
		query.chaincodes
			.asFlow()
			.flatMapConcat {
				val chaincode = it.burstChaincode()!!
				CouchdbSsmListQuery(
					chaincodeId = chaincode.chaincodeId,
					channelId = chaincode.channelId,
					pagination = null
				).queryEachChannel(chaincode)
			}.let {
				DataSsmListQueryResult(it.toList())
			}
	}

	private suspend fun CouchdbSsmListQuery.queryEachChannel(chaincode: ChaincodeUriBurstDTO): Flow<DataSsm> =
		couchdbSsmListQueryFunction(this)
			.let { result ->
				result.page.list.asFlow().map {
					it.toDataSsm(chaincode)
				}
			}.filterNotNull()
}

