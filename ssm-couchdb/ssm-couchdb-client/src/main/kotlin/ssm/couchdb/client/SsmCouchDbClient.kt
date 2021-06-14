package ssm.couchdb.client

import com.ibm.cloud.cloudant.v1.Cloudant
import com.ibm.cloud.cloudant.v1.model.DatabaseInformation
import com.ibm.cloud.cloudant.v1.model.FindResult
import com.ibm.cloud.cloudant.v1.model.GetDatabaseInformationOptions
import com.ibm.cloud.cloudant.v1.model.PostFindOptions
import com.ibm.cloud.cloudant.v1.model.PostViewOptions
import com.ibm.cloud.sdk.core.http.Response
import ssm.chaincode.dsl.DocType
import ssm.couchdb.client.builder.SsmCouchDbClientBuilder
import ssm.sdk.json.JSONConverter

class SsmCouchDbClient(
	val cloudant: Cloudant,
	private val converter: JSONConverter,
) {

	companion object {
		const val COUNTING_VIEW = "indexType"
		const val FABRIC_COUNTING_DDOC = "indexTypeDoc"

		fun builder(): SsmCouchDbClientBuilder {
			return SsmCouchDbClientBuilder()
		}
	}

	fun <T: Any> fetchAllByDocType(dbName: String, docType: DocType<T>, filters: Map<String, Any> = emptyMap()): List<T> {
		val selector = mapOf(
			"docType" to mapOf("\$eq" to docType.docType)
		).plus(filters)

		val findOptions = PostFindOptions.Builder()
			.db(dbName)
			.selector(selector)
			.limit(Long.MAX_VALUE)
			.build()

		val result: Response<FindResult> = cloudant.postFind(findOptions).execute()

		return result.result.docs.mapNotNull { document ->
			converter.toObject(docType.clazz.java).apply(document.toString()).orElse(null)
		}
	}

	fun getDatabases(): List<String> {
		return cloudant.allDbs.execute().result
	}

	fun getDatabase(dbName: String): DatabaseInformation {
		val query = GetDatabaseInformationOptions.Builder().db(dbName).build()
		return cloudant.getDatabaseInformation(query).execute().result
	}


	fun <T: Any> getCount(dbName: String, docType: DocType<T>): Int {
		val query = PostViewOptions.Builder()
			.db(dbName)
			.ddoc(FABRIC_COUNTING_DDOC)
			.view(COUNTING_VIEW)
			.groupLevel(1)
			.key(arrayOf(docType.docType))
			.build()
		return (cloudant.postView(query).execute().result.rows.first().value as Double).toInt()
	}
}

