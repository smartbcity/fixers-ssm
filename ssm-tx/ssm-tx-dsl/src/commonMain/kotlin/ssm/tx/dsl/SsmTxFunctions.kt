package ssm.tx.dsl

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.tx.dsl.features.user.SsmUserRegisterFunction
import ssm.tx.dsl.features.user.SsmUserGrantFunction

/**
 * - fun ssmUserGrantFunction(config: DataSsmConfig): [SsmUserGrantFunction]
 * - fun dataSsmListQueryFunction(config: DataSsmConfig): [DataSsmListQueryFunction]
 * @d2 model
 * @title Entrypoints
 * @page
 * Synthesis and global objects of the API
 * @@title SSM-TX/General
 */
interface SsmTxFunctions {
	fun ssmUserGrantFunction(config: SsmChaincodeConfig): SsmUserGrantFunction
	fun ssmUserRegisterFunction(config: SsmChaincodeConfig): SsmUserRegisterFunction
}
