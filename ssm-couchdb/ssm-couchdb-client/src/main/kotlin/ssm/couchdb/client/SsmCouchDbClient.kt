package ssm.couchdb.client

import com.ibm.cloud.cloudant.v1.Cloudant
import com.ibm.cloud.cloudant.v1.model.DatabaseInformation
import com.ibm.cloud.cloudant.v1.model.FindResult
import com.ibm.cloud.cloudant.v1.model.GetDatabaseInformationOptions
import com.ibm.cloud.cloudant.v1.model.PostFindOptions
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

	fun getAllAdmins(dbName: String): List<SsmAgent> {
		return fetchAllByDocType(dbName, DocType.Admin)
	}

	fun getAllUsers(dbName: String): List<SsmAgent> {
		return fetchAllByDocType(dbName, DocType.User)
	}

	fun getAllSsm(dbName: String): List<Ssm> {
		return fetchAllByDocType(dbName, DocType.Ssm)
	}

	fun getAllStates(dbName: String): List<SsmSession> {
		return fetchAllByDocType(dbName, DocType.State)
	}

	fun getAllGrants(dbName: String): List<SsmGrant> {
		return fetchAllByDocType(dbName, DocType.Grant)
	}

	private fun <T : Any> fetchAllByDocType(dbName: String, docType: DocType<T>): List<T> {
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
}

