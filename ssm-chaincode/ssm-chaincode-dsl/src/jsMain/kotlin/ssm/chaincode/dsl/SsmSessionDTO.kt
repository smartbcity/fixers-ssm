package ssm.chaincode.dsl

@JsExport
@JsName("SsmSessionDTO")
actual external interface SsmSessionDTO : WithPrivate {
	actual val ssm: String?
	actual val session: String
	actual val roles: Map<String, String>?
	actual val public: Any?
	actual abstract override val private: Map<String, String>?
}