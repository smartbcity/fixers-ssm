package ssm.chaincode.dsl

@JsExport
@JsName("SsmContextDTO")
actual external interface SsmContextDTO : WithPrivate {
	actual val session: String
	actual val public: String
	actual val iteration: Int
	actual abstract override val private: Map<String, String>?
}