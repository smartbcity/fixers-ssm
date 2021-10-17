package ssm.chaincode.dsl.query

import f2.dsl.cqrs.Event
import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.chaincode.dsl.SsmQueryDTO

typealias SsmListAdminQueryFunction = F2Function<SsmListAdminQuery, SsmListAdminResult>

@Serializable
@JsExport
@JsName("SsmListAdminQuery")
class SsmListAdminQuery(
	override val bearerToken: String? = null,
 ): SsmQueryDTO

@Serializable
@JsExport
@JsName("SsmListAdminResult")
class SsmListAdminResult(
	val values: Array<String>,
) : Event
