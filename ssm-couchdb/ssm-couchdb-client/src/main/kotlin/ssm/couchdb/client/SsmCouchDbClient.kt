package ssm.couchdb.client

import com.ibm.cloud.cloudant.v1.Cloudant
import com.ibm.cloud.cloudant.v1.model.*
import com.ibm.cloud.sdk.core.http.Response
import ssm.couchdb.client.builder.SsmCouchDbClientBuilder
import ssm.dsl.*
import ssm.sdk.json.JSONConverter

class SsmCouchDbClient(
	val cloudant: Cloudant,
	private val converter: JSONConverter,
) {

	companion object {
		fun builder(): SsmCouchDbClientBuilder {
			return SsmCouchDbClientBuilder()
		}
	}

	fun <T : Any> fetchAllByDocType(dbName: String, docType: DocType<T>): List<T> {
		val selector = mapOf(
			"docType" to mapOf("\$eq" to docType.docType)
		)
		val findOptions = PostFindOptions.Builder()
			.db(dbName)
			.selector(selector)
			.build()

		val result: Response<FindResult> = cloudant.postFind(findOptions).execute()

		return result.result.docs.map { document ->
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


	fun <T : Any> getCount(dbName: String, docType: DocType<T>): Int {
		val query = PostViewOptions.Builder()
			.db(dbName)
			.ddoc(FABRIC_COUNTING_DDOC())
			.view(COUNTING_VIEW())
			.groupLevel(1)
			.key(arrayOf(docType.docType))
			.build()
		return (cloudant.postView(query).execute().result.rows.first().value as Double).toInt()
	}

	private fun COUNTING_VIEW() = "indexType"

	private fun FABRIC_COUNTING_DDOC() = "indexTypeDoc"

}

