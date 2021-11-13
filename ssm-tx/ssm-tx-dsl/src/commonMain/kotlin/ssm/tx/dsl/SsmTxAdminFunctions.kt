package ssm.tx.dsl

import ssm.chaincode.dsl.config.ChaincodeSsmConfig
import ssm.tx.dsl.features.ssm.SsmTxCreateFunction
import ssm.tx.dsl.features.ssm.SsmTxInitializeFunction
import ssm.tx.dsl.features.ssm.SsmTxSessionStartFunction
import ssm.tx.dsl.features.user.SsmTxUserGrantFunction
import ssm.tx.dsl.features.user.SsmTxUserRegisterFunction

/**
 * - fun ssmTxUserGrantFunction(config: SsmChaincodeConfig): [SsmTxUserGrantFunction]
 * - fun ssmTxUserRegisterFunction(config: SsmChaincodeConfig): [SsmTxUserRegisterFunction]
 *
 * - fun ssmTxCreateFunction(config: SsmChaincodeConfig): [SsmTxCreateFunction]
 * - fun ssmTxInitializeFunction(config: SsmChaincodeConfig): [SsmTxInitializeFunction]
 * - fun ssmTxSessionStartFunction(config: SsmChaincodeConfig): [SsmTxSessionStartFunction]
 * @d2 model
 * @title Admin Agent command
 * @parent [ssm.tx.dsl.SsmTxD2]
 */
interface SsmTxAdminFunctions {
	fun ssmTxUserGrantFunction(config: ChaincodeSsmConfig): SsmTxUserGrantFunction
	fun ssmTxUserRegisterFunction(config: ChaincodeSsmConfig): SsmTxUserRegisterFunction

	fun ssmTxCreateFunction(config: ChaincodeSsmConfig): SsmTxCreateFunction
	fun ssmTxInitializeFunction(config: ChaincodeSsmConfig): SsmTxInitializeFunction
	fun ssmTxSessionStartFunction(config: ChaincodeSsmConfig): SsmTxSessionStartFunction
}
