package ssm.tx.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.tx.dsl.model.TxSsm

/**
 * Retrieves a given SSM
 * @d2 function
 * @parent [TxSsm]
 * @order 10
 * @title Get SSM
 */
typealias TxSsmGetQueryFunction = F2Function<TxSsmGetQueryDTO, TxSsmGetQueryResultDTO>

expect interface TxSsmGetQueryDTO : TxQueryDTO {
	override val ssm: SsmName
	override val bearerToken: String?
}

/**
 * @d2 query
 * @parent [TxSsmGetQueryFunction]
 * @title Get SSM: Parameters
 */
@Serializable
@JsExport
@JsName("TxSsmGetQuery")
class TxSsmGetQuery(
	override val ssm: SsmName,
	override val bearerToken: String?,
) : TxSsmGetQueryDTO

expect interface TxSsmGetQueryResultDTO {
	/**
	 * The retrieved SSM if it exists
	 */
	val ssm: TxSsm?
}

/**
 * @d2 event
 * @parent [TxSsmGetQueryFunction]
 * @title Get SSM: Result
 */
@Serializable
@JsExport
@JsName("TxSsmGetQueryResult")
class TxSsmGetQueryResult(
	override val ssm: TxSsm?,
) : TxSsmGetQueryResultDTO
