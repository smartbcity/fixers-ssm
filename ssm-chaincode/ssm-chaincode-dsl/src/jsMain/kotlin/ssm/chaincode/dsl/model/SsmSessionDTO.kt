package ssm.chaincode.dsl.model

import ssm.chaincode.dsl.model.WithPrivate

@JsExport
@JsName("SsmSessionDTO")
actual external interface SsmSessionDTO : WithPrivate {
	actual val ssm: String?
	actual val session: String
	actual val roles: Map<String, String>?
	actual val public: Any?
	actual override val private: Map<String, String>?
}
