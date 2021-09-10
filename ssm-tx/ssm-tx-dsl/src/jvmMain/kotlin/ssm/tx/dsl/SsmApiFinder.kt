package ssm.tx.dsl

import ssm.tx.dsl.features.query.TxSsmGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionListQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogGetQueryFunction
import ssm.tx.dsl.features.query.TxSsmSessionLogListQueryFunction

actual interface SsmApiFinder {
	actual fun txSsmListQueryFunction(): TxSsmListQueryFunction
	actual fun txSsmGetQueryFunction(): TxSsmGetQueryFunction
	actual fun txSsmSessionListQueryFunction(): TxSsmSessionListQueryFunction
	actual fun txSsmSessionGetQueryFunction(): TxSsmSessionGetQueryFunction
	actual fun txSsmSessionLogListQueryFunction(): TxSsmSessionLogListQueryFunction
	actual fun txSsmSessionLogGetQueryFunction(): TxSsmSessionLogGetQueryFunction
}
