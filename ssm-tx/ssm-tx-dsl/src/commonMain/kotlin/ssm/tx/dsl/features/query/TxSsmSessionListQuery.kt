package ssm.tx.dsl.features.query

import f2.dsl.fnc.F2Function
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import ssm.tx.dsl.model.TxSsmSession
import ssm.tx.dsl.model.TxSsmSessionDTO

/**
 * Retrieve a list of all known sessions of a given SSM
 * @d2 function
 * @parent [TxSsmSession]
 * @order 20
 * @title List Sessions
 */
typealias TxSsmSessionListQueryFunction = F2Function<TxSsmSessionListQueryDTO, TxSsmSessionListQueryResultDTO>

expect interface TxSsmSessionListQueryDTO : TxQueryDTO

/**
 * @d2 query
 * @parent [TxSsmSessionListQueryFunction]
 * @title List Sessions: Parameters
 */
@Serializable
@JsExport
@JsName("TxSsmSessionListQuery")
class TxSsmSessionListQuery(
	override val ssm: String,
	override val bearerToken: String?,
) : TxSsmSessionListQueryDTO

expect interface TxSsmSessionListQueryResultDTO {
	/**
	 * List of all the retrieved sessions
	 */
	val list: List<TxSsmSessionDTO>
}

/**
 * @d2 event
 * @parent [TxSsmSessionListQueryFunction]
 * @title List Sessions: Result
 */
@Serializable
@JsExport
@JsName("TxSsmSessionListQueryResult")
class TxSsmSessionListQueryResult(
	override val list: List<TxSsmSession>,
) : TxSsmSessionListQueryResultDTO
