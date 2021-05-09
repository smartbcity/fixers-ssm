package ssm.couchdb.f2.query

import com.ibm.cloud.cloudant.v1.model.DatabaseInformation
import f2.dsl.function.F2Function
import f2.function.spring.adapter.f2Function
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.couchdb.client.SsmCouchDbClient

@Configuration
class CdbGetDatabaseFunctionImpl(
	private val client: SsmCouchDbClient,
) {

	@Bean
	fun cdbGetDatabaseFunction(): F2Function<String, DatabaseInformation?> = f2Function { dbName ->
		client.getDatabase(dbName)
	}

}