package ssm.couchdb.dsl.model

actual interface DatabaseChangesDTO {
	actual val changeEventId: ChangeEventId
	actual val docType: DocType<*>?
	actual val objectId: String
}
