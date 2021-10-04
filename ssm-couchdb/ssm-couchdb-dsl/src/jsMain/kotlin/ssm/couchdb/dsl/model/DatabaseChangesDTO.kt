package ssm.couchdb.dsl.model

@JsExport
@JsName("DatabaseChangesDTO")
actual external interface DatabaseChangesDTO {
	actual val changeEventId: ChangeEventId
	actual val docType: DocType<*>?
	actual val objectId: String
}
