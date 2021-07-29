package ssm.tx.dsl.features.query

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("TxQueryDTO")
interface TxQueryDTO {
    val ssm: SsmName
    val bearerToken: String?
}

typealias SsmName = String