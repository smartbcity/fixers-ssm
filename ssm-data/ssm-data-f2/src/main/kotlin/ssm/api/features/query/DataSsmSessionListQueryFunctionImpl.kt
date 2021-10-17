package ssm.api.features.query

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import ssm.api.extentions.toDataSession
import ssm.chaincode.dsl.model.uri.burstSsmUri
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQuery
import ssm.couchdb.f2.query.CouchdbSsmSessionStateListQueryFunctionImpl
import ssm.data.dsl.config.DataSsmConfig
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryResult

class DataSsmSessionListQueryFunctionImpl(private val config: DataSsmConfig) {

	fun dataSsmSessionListQueryFunction(): DataSsmSessionListQueryFunction = f2Function { query ->
		val burst = query.ssm.burstSsmUri()!!
		val couchdbQuery = CouchdbSsmSessionStateListQuery(
			channelId = burst.channelId,
			chaincodeId = burst.chaincodeId,
			ssm = burst.ssmName
		)
		CouchdbSsmSessionStateListQueryFunctionImpl(config.couchdb).couchdbSsmSessionStateListQueryFunction().invoke(
			couchdbQuery
		).page.list
			.filter { sessionState -> sessionState.session.isNotBlank() }
			.map { sessionState ->
				sessionState.toDataSession(config, query.ssm, query.bearerToken)
			}.let {
				DataSsmSessionListQueryResult(it)
			}
	}
}
