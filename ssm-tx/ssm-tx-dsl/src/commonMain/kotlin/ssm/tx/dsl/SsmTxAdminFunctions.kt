package ssm.tx.dsl

import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.tx.dsl.features.ssm.SsmTxCreateFunction
import ssm.tx.dsl.features.ssm.SsmTxInitializeFunction
import ssm.tx.dsl.features.ssm.SsmTxSessionStartFunction
import ssm.tx.dsl.features.user.SsmTxUserGrantFunction
import ssm.tx.dsl.features.user.SsmTxUserRegisterFunction

/**
 * - fun ssmTxUserGrantFunction(): [SsmTxUserGrantFunction]
 * - fun ssmTxUserRegisterFunction(): [SsmTxUserRegisterFunction]
 *
 * - fun ssmTxCreateFunction(): [SsmTxCreateFunction]
 * - fun ssmTxInitializeFunction(): [SsmTxInitializeFunction]
 * - fun ssmTxSessionStartFunction(): [SsmTxSessionStartFunction]
 * @d2 model
 * @title Admin Agent command
 * @parent [ssm.tx.dsl.SsmTxD2]
 */
interface SsmTxAdminFunctions {
	fun ssmTxUserGrantFunction(): SsmTxUserGrantFunction
	fun ssmTxUserRegisterFunction(): SsmTxUserRegisterFunction

	fun ssmTxCreateFunction(): SsmTxCreateFunction
	fun ssmTxInitializeFunction(): SsmTxInitializeFunction
	fun ssmTxSessionStartFunction(): SsmTxSessionStartFunction
}
