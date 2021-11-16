package ssm.chaincode.dsl.model

actual interface SsmSessionDTO : WithPrivate {
	actual val ssm: SsmName?
	actual val session: SessionName
	actual val roles: Map<String, String>?
	actual val public: Any?
	actual override val private: Map<String, String>?
}
