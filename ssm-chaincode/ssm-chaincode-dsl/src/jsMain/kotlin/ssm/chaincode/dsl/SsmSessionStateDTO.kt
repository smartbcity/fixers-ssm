package ssm.chaincode.dsl

@JsExport
@JsName("SsmSessionStateDTO")
actual external interface SsmSessionStateDTO : SsmSessionDTO, WithPrivate {
	actual override val ssm: String?
	actual override val session: String
	actual override val roles: Map<String, String>?
	actual override val public: Any?
	actual override val private: Map<String, String>?
	actual val origin: SsmTransitionDTO?
	actual val current: Int
	actual val iteration: Int
}
