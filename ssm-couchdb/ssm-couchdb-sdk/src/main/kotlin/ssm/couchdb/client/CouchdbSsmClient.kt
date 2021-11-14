package ssm.couchdb.client

import com.ibm.cloud.cloudant.v1.Cloudant
import com.ibm.cloud.cloudant.v1.model.ChangesResult
import com.ibm.cloud.cloudant.v1.model.DatabaseInformation
import com.ibm.cloud.cloudant.v1.model.FindResult
import com.ibm.cloud.cloudant.v1.model.GetDatabaseInformationOptions
import com.ibm.cloud.cloudant.v1.model.PostChangesOptions
import com.ibm.cloud.cloudant.v1.model.PostFindOptions
import com.ibm.cloud.cloudant.v1.model.PostViewOptions
import com.ibm.cloud.sdk.core.http.Response
import ssm.couchdb.client.builder.SsmCouchDbClientBuilder
import ssm.couchdb.dsl.model.ChangeEventId
import ssm.couchdb.dsl.model.DatabaseName
import ssm.couchdb.dsl.model.DocType
import ssm.sdk.json.JSONConverter

class CouchdbSsmClient(
	val cloudant: Cloudant,
	private val converter: JSONConverter,
) {

	companion object {
		const val COUNTING_VIEW = "indexType"
		const val FABRIC_COUNTING_DOC = "indexTypeDoc"

		fun builder(): SsmCouchDbClientBuilder {
			return SsmCouchDbClientBuilder()
		}
	}

	fun <T : Any> fetchAllByDocType(
		dbName: String,
		docType: DocType<T>,
		filters: Map<String, String> = emptyMap(),
	): List<T> {
		val selector = mapOf(
			"docType" to mapOf("\$eq" to docType.name)
		).plus(filters)

		val findOptions = PostFindOptions.Builder()
			.db(dbName)
			.selector(selector)
			.limit(Long.MAX_VALUE)
			.build()

		val result: Response<FindResult> = cloudant.postFind(findOptions).execute()

		return result.result.docs.mapNotNull { document ->
			converter.toObject(docType.clazz.java, document.toString())
		}
	}

	fun <T : Any> fetchOneByDocTypeAndName(
		dbName: String,
		docType: DocType<T>,
		name: String,
	): T? {
		val selector = mapOf(
			"docType" to mapOf("\$eq" to docType.name),
			"name" to name
		)

		val findOptions = PostFindOptions.Builder()
			.db(dbName)
			.selector(selector)
			.limit(Long.MAX_VALUE)
			.build()

		val result: Response<FindResult> = cloudant.postFind(findOptions).execute()

		return result.result.docs.firstOrNull()?.let { document ->
			converter.toObject(docType.clazz.java, document.toString())
		}
	}

	fun getDatabases(): List<String> {
		return cloudant.allDbs.execute().result
	}

	fun getDatabase(dbName: String): DatabaseInformation {
		val query = GetDatabaseInformationOptions.Builder().db(dbName).build()
		return cloudant.getDatabaseInformation(query).execute().result
	}

	fun getChanges(dbName: DatabaseName, lastEventId: ChangeEventId? = null): ChangesResult {
		val query = PostChangesOptions.Builder()
			.db(dbName)
			.lastEventId(lastEventId)
			.includeDocs(true)
			.build()
		return cloudant.postChanges(query).execute().result
	}

	fun <T : Any> getCount(dbName: String, docType: DocType<T>): Int {
		val query = PostViewOptions.Builder()
			.db(dbName)
			.ddoc(FABRIC_COUNTING_DOC)
			.view(COUNTING_VIEW)
			.groupLevel(1)
			.key(arrayOf(docType.name))
			.build()
		return (cloudant.postView(query).execute().result.rows.firstOrNull()?.value as Double?)?.toInt() ?: 0
	}
}
