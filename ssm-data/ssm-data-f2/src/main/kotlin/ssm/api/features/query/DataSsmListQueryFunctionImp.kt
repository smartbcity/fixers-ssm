package ssm.api.features.query

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import ssm.api.extentions.toDataSsm
import ssm.couchdb.dsl.query.CouchdbSsmListQuery
import ssm.couchdb.f2.query.CouchdbSsmListQueryFunctionImpl
import ssm.data.dsl.config.DataSsmConfig
import ssm.data.dsl.features.query.DataSsmListQueryFunction
import ssm.data.dsl.features.query.DataSsmListQueryResult

class DataSsmListQueryFunctionImp(
	private val config: DataSsmConfig
) {

	fun dataSsmListQueryFunction(): DataSsmListQueryFunction = f2Function { query ->
		CouchdbSsmListQueryFunctionImpl(config.couchdb)
			.couchdbSsmListQueryFunction()
			.invoke(
				CouchdbSsmListQuery(
					chaincodeId = config.chaincodeId,
					channelId = config.channelId,
					pagination = null
				)
			).page.list
			.asFlow()
			.map { result ->
				result.toDataSsm(config)
			}.let {
				val dataSsmListQueryResult = DataSsmListQueryResult(it.toList())
				dataSsmListQueryResult
			}
	}
}
