package ssm.chaincode.dsl.model

import ssm.chaincode.dsl.model.WithPrivate

actual interface SsmContextDTO : WithPrivate {
	actual val session: String
	actual val public: String
	actual val iteration: Int
	actual override val private: Map<String, String>?
}
