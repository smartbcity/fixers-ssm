package ssm.tx.dsl

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.tx.dsl.features.ssm.SsmTxSessionPerformActionFunction
import ssm.tx.dsl.features.user.SsmTxUserGrantFunction

/**
 * - fun ssmUserGrantFunction(config: DataSsmConfig): [SsmTxUserGrantFunction]
 * - fun dataSsmListQueryFunction(config: DataSsmConfig): [DataSsmListQueryFunction]
 * @d2 model
 * @title Entrypoints
 * @page
 * Synthesis and global objects of the API
 * @@title SSM-TX/General
 */
interface SsmTxUserFunctions {
	fun ssmTxSessionPerformActionFunction(config: SsmChaincodeConfig): SsmTxSessionPerformActionFunction
}
