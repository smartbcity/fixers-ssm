package ssm.tx.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.tx.dsl.model.TxSsm
import ssm.tx.dsl.model.TxSsmDTO

/**
 * Retrieves all known SSMs
 * @d2 function
 * @parent [TxSsm]
 * @order 20
 * @title List SSMs
 */
typealias TxSsmListQueryFunction = F2Function<TxSsmListQueryDTO, TxSsmListQueryResultDTO>

expect interface TxSsmListQueryDTO

/**
 * @d2 query
 * @parent [TxSsmListQueryFunction]
 * @title List SSMs: Parameters
 */
@Serializable
@JsExport
@JsName("TxSsmListQuery")
class TxSsmListQuery : TxSsmListQueryDTO

expect interface TxSsmListQueryResultDTO {
	/**
	 * List of all retrieved SSMs
	 */
	val list: List<TxSsmDTO>
}

/**
 * @d2 event
 * @parent [TxSsmListQueryFunction]
 * @title List SSMs: Result
 */
@Serializable
@JsExport
@JsName("TxSsmListQueryResult")
class TxSsmListQueryResult(
	override val list: List<TxSsm>,
) : TxSsmListQueryResultDTO
