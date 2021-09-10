package ssm.chaincode.dsl

@JsExport
@JsName("WithPrivate")
actual external interface WithPrivate {
	actual val private: Map<String, String>?
}
