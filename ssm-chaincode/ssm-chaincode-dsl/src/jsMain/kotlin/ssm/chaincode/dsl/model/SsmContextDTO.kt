package ssm.chaincode.dsl.model

@JsExport
@JsName("SsmContextDTO")
actual external interface SsmContextDTO : WithPrivate {
	actual val session: String
	actual val public: String
	actual val iteration: Int
	actual override val private: Map<String, String>?
}
