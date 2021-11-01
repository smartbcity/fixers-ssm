package ssm.api.features.query

import f2.dsl.fnc.f2Function
import f2.dsl.fnc.invoke
import ssm.api.extentions.toDataSession
import ssm.chaincode.dsl.model.uri.ChaincodeUriBurst
import ssm.chaincode.dsl.model.uri.burstSsmUri
import ssm.chaincode.dsl.model.uri.compact
import ssm.couchdb.dsl.query.CouchdbSsmSessionStateListQuery
import ssm.couchdb.f2.query.CouchdbSsmSessionStateListQueryFunctionImpl
import ssm.data.dsl.config.SsmDataConfig
import ssm.data.dsl.features.query.DataSsmSessionListQueryFunction
import ssm.data.dsl.features.query.DataSsmSessionListQueryResult

class DataSsmSessionListQueryFunctionImpl(private val config: SsmDataConfig) {

	fun dataSsmSessionListQueryFunction(): DataSsmSessionListQueryFunction = f2Function { query ->
		val burst = query.ssm.burstSsmUri()!!
		val couchdbQuery = CouchdbSsmSessionStateListQuery(
			chaincodeUri = ChaincodeUriBurst(
				channelId = burst.channelId,
				chaincodeId = burst.chaincodeId,
			).compact(),
			ssm = burst.ssmName,
			pagination = null
		)
		CouchdbSsmSessionStateListQueryFunctionImpl(config.couchdb).couchdbSsmSessionStateListQueryFunction().invoke(
			couchdbQuery
		).page.list
			.filter { sessionState -> sessionState.session.isNotBlank() }
			.map { sessionState ->
				sessionState.toDataSession(config, query.ssm)
			}.let {
				DataSsmSessionListQueryResult(it)
			}
	}
}
