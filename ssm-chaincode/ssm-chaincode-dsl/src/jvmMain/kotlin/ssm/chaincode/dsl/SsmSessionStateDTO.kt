package ssm.chaincode.dsl

actual interface SsmSessionStateDTO : SsmSessionDTO, WithPrivate {
	actual abstract override val ssm: String?
	actual abstract override val session: String
	actual abstract override val roles: Map<String, String>?
	actual abstract override val public: Any?
	actual abstract override val private: Map<String, String>?
	actual val origin: SsmTransitionDTO?
	actual val current: Int
	actual val iteration: Int
}