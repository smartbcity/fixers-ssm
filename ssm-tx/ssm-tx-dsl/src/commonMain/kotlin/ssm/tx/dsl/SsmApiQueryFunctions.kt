package ssm.tx.dsl

import ssm.tx.dsl.config.SsmTxConfig
import ssm.tx.dsl.features.query.TxSsmGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogListQueryFunction

/**
 * - fun txSsmListQueryFunction(config: SsmTxConfig): [TxSsmListQueryFunction]
 * - fun txSsmGetQueryFunction(config: SsmTxConfig): [TxSsmGetQueryFunction]
 * - fun txSsmSessionListQueryFunction(config: SsmTxConfig): [TxSsmSessionListQueryFunction]
 * - fun txSsmSessionGetQueryFunction(config: SsmTxConfig): [TxSsmSessionGetQueryFunction]
 * - fun txSsmSessionLogListQueryFunction(config: SsmTxConfig): [TxSsmSessionLogListQueryFunction]
 * - fun txSsmSessionLogGetQueryFunction(config: SsmTxConfig): [TxSsmSessionLogGetQueryFunction]
 * @d2 model
 * @title Entrypoints
 * @page
 * Synthesis and global objects of the API
 * @@title SSM-TX/General
 */
expect interface SsmApiQueryFunctions {
	fun txSsmListQueryFunction(config: SsmTxConfig): TxSsmListQueryFunction
	fun txSsmGetQueryFunction(config: SsmTxConfig): TxSsmGetQueryFunction
	fun txSsmSessionListQueryFunction(config: SsmTxConfig): TxSsmSessionListQueryFunction
	fun txSsmSessionGetQueryFunction(config: SsmTxConfig): TxSsmSessionGetQueryFunction
	fun txSsmSessionLogListQueryFunction(config: SsmTxConfig): TxSsmSessionLogListQueryFunction
	fun txSsmSessionLogGetQueryFunction(config: SsmTxConfig): TxSsmSessionLogGetQueryFunction
}
