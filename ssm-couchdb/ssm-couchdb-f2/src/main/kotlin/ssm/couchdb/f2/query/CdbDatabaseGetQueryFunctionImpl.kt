package ssm.couchdb.f2.query

import com.ibm.cloud.cloudant.v1.model.DatabaseInformation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.Database
import ssm.couchdb.dsl.query.CdbDatabaseGetQueryFunction
import ssm.couchdb.dsl.query.CdbSsmDatabaseGetQueryResult
import ssm.couchdb.f2.commons.CdbF2Function

@Configuration
class CdbDatabaseGetQueryFunctionImpl(
	private val cbdf2: CdbF2Function,
) {

	@Bean
	fun cdbDatabaseGetQueryFunction(): CdbDatabaseGetQueryFunction = cbdf2.function { cmd, cdbClient ->
		CdbSsmDatabaseGetQueryResult(
			item = cdbClient.getDatabase(cmd.dbName).asDatabase()
		)
	}

	private fun DatabaseInformation.asDatabase() = Database(this.dbName)
}
