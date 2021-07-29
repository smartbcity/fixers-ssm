package ssm.tx.dsl

import ssm.tx.dsl.features.query.*


actual interface SsmApiFinder {
	actual fun txSsmListQueryFunction(): TxSsmListQueryFunction
	actual fun txSsmGetQueryFunction(): TxSsmGetQueryFunction
	actual fun txSsmSessionListQueryFunction(): TxSsmSessionListQueryFunction
	actual fun txSsmSessionGetQueryFunction(): TxSsmSessionGetQueryFunction
	actual fun txSsmSessionLogListQueryFunction(): TxSsmSessionLogListQueryFunction
	actual fun txSsmSessionLogGetQueryFunction(): TxSsmSessionLogGetQueryFunction
}
