package ssm.chaincode.dsl.model

actual interface SsmSessionStateDTO : SsmSessionDTO, WithPrivate {
	actual override val ssm: SsmName?
	actual override val session: SessionName
	actual override val roles: Map<String, String>?
	actual override val public: Any?
	actual override val private: Map<String, String>?
	actual val origin: SsmTransitionDTO?
	actual val current: Int
	actual val iteration: Int
}
