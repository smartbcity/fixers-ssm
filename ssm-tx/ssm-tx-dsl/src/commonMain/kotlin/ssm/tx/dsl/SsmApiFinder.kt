package ssm.tx.dsl

import ssm.tx.dsl.features.query.TxSsmGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogListQueryFunction

/**
 * - fun txSsmListQueryFunction(): [TxSsmListQueryFunction]
 * - fun txSsmGetQueryFunction(): [TxSsmGetQueryFunction]
 * - fun txSsmSessionListQueryFunction(): [TxSsmSessionListQueryFunction]
 * - fun txSsmSessionGetQueryFunction(): [TxSsmSessionGetQueryFunction]
 * - fun txSsmSessionLogListQueryFunction(): [TxSsmSessionLogListQueryFunction]
 * - fun txSsmSessionLogGetQueryFunction(): [TxSsmSessionLogGetQueryFunction]
 * @d2 model
 * @title Entrypoints
 * @page
 * Synthesis and global objects of the API
 * @@title SSM-TX/General
 */
expect interface SsmApiFinder {
	fun txSsmListQueryFunction(): TxSsmListQueryFunction
	fun txSsmGetQueryFunction(): TxSsmGetQueryFunction
	fun txSsmSessionListQueryFunction(): TxSsmSessionListQueryFunction
	fun txSsmSessionGetQueryFunction(): TxSsmSessionGetQueryFunction
	fun txSsmSessionLogListQueryFunction(): TxSsmSessionLogListQueryFunction
	fun txSsmSessionLogGetQueryFunction(): TxSsmSessionLogGetQueryFunction
}
