package ssm.couchdb.f2.query

import com.ibm.cloud.cloudant.v1.Cloudant
import com.ibm.cloud.sdk.core.http.Response
import com.ibm.cloud.sdk.core.http.ServiceCall
import f2.dsl.function.F2Supplier
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CdbGetDatabasesFunctionImpl(
	private val client: Cloudant,
) {

	@Bean
	fun cdbGetDatabasesFunction(): F2Supplier<String> = {
		client.allDbs.execute().result.asFlow().onEach { db ->
			println(db)
		}
	}

}