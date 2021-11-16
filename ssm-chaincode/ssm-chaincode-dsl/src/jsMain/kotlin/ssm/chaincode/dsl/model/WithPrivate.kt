package ssm.chaincode.dsl.model

@JsExport
@JsName("WithPrivate")
actual external interface WithPrivate {
	actual val private: Map<String, String>?
}
