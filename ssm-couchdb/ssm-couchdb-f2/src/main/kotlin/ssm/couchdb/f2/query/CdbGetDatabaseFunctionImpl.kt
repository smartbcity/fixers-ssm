package ssm.couchdb.f2.query

import com.ibm.cloud.cloudant.v1.model.DatabaseInformation
import f2.dsl.function.F2Function
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.dsl.query.CdbGetDatabaseQuery
import ssm.couchdb.f2.commons.cdbF2Function

@Configuration
class CdbGetDatabaseFunctionImpl {

	@Bean
	fun cdbGetDatabaseFunction(): F2Function<CdbGetDatabaseQuery, DatabaseInformation?> = cdbF2Function { cmd, cdbClient ->
		cdbClient.getDatabase(cmd.dbName)
	}
}
