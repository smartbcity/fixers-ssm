package ssm.couchdb.f2.query

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.SsmSessionStateDTO
import ssm.couchdb.dsl.DocType
import ssm.couchdb.dsl.query.CdbSsmSessionListQueryFunction
import ssm.couchdb.dsl.query.CdbSsmSessionListQueryResult
import ssm.couchdb.f2.commons.CdbF2Function

@Configuration
class CdbSsmSessionListQueryFunctionImpl(
	private val cbdf2: CdbF2Function,
) {

	@Bean
	fun cdbSsmSessionListQueryFunction(): CdbSsmSessionListQueryFunction = cbdf2.function { cmd, cdbClient ->
		val filters = cmd.ssm?.let { ssm ->
			mapOf(SsmSessionStateDTO::ssm.name to ssm)
		} ?: emptyMap()

		cdbClient.fetchAllByDocType(cmd.dbName, DocType.State, filters)
			.toTypedArray()
			.let(::CdbSsmSessionListQueryResult)
	}
}
