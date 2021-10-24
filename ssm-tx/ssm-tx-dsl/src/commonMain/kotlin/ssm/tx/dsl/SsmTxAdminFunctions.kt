package ssm.tx.dsl

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.tx.dsl.features.ssm.SsmTxInitializeFunction
import ssm.tx.dsl.features.ssm.SsmTxCreateFunction
import ssm.tx.dsl.features.ssm.SsmTxSessionPerformActionFunction
import ssm.tx.dsl.features.ssm.SsmTxSessionStartFunction
import ssm.tx.dsl.features.user.SsmTxUserGrantFunction
import ssm.tx.dsl.features.user.SsmTxUserRegisterFunction

/**
 * 	- fun ssmTxCreateFunction(config: SsmChaincodeConfig): [SsmTxCreateFunction]
 * 	- fun ssmInitializeFunction(config: SsmChaincodeConfig): [SsmTxInitializeFunction]
 * 	- fun ssmTxSessionPerformActionFunction(config: SsmChaincodeConfig): [SsmTxSessionPerformActionFunction]
 * 	- fun ssmTxSessionStartFunction(config: SsmChaincodeConfig): [SsmTxSessionStartFunction]
 * @d2 model
 * @title Entrypoints
 * @page
 * Synthesis and global objects of the API
 * @@title SSM-TX/General
 */
interface SsmTxAdminFunctions {

	fun ssmTxUserGrantFunction(config: SsmChaincodeConfig): SsmTxUserGrantFunction
	fun ssmTxUserRegisterFunction(config: SsmChaincodeConfig): SsmTxUserRegisterFunction

	fun ssmTxCreateFunction(config: SsmChaincodeConfig): SsmTxCreateFunction
	fun ssmTxInitializeFunction(config: SsmChaincodeConfig): SsmTxInitializeFunction
	fun ssmTxSessionStartFunction(config: SsmChaincodeConfig): SsmTxSessionStartFunction
}
