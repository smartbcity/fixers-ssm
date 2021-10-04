package ssm.couchdb.client

import org.junit.jupiter.api.Test
import ssm.couchdb.client.test.DataTest

internal class SsmCouchDbClientTest {

	@Test
	fun `verify lastEventId is working`() {
//		val allChanges = DataTest.ssmCouchDbClient.getChanges(DataTest.ssmName)
//		Assertions.assertThat(allChanges.results).isNotEmpty
//		val last = allChanges.results.last()
//
//		val changeAfter = DataTest.ssmCouchDbClient.getChanges(DataTest.ssmName, last.seq)
//		Assertions.assertThat(changeAfter.results).isEmpty()
	}

	@Test
	fun `explore ssmCouchDb getChanges return type`() {
		val allChanges = DataTest.ssmCouchDbClient.getChanges(DataTest.ssmName)
		allChanges.results.forEach { item ->
			println("//////////////////////")
			println("seq: ${item.seq}")
			println("doc: ${item.doc}")
			println("id: ${item.id}")
			println("docType: ${item.getDocType()?.clazz?.simpleName}")
			println("isDeleted: ${item.isDeleted}")
			item.changes.forEach { change ->
				println(change)
			}
			println("//////////////////////")
		}
	}
}