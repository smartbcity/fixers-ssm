package ssm.chaincode.dsl

import ssm.chaincode.dsl.config.SsmChaincodeConfig
import ssm.chaincode.dsl.query.SsmGetAdminFunction
import ssm.chaincode.dsl.query.SsmGetQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionQueryFunction
import ssm.chaincode.dsl.query.SsmGetTransactionQueryFunction
import ssm.chaincode.dsl.query.SsmGetUserFunction
import ssm.chaincode.dsl.query.SsmListAdminQueryFunction
import ssm.chaincode.dsl.query.SsmListSessionQueryFunction
import ssm.chaincode.dsl.query.SsmListSsmQueryFunction
import ssm.chaincode.dsl.query.SsmListUserQueryFunction

/**
 * - fun ssmGetAdminFunction(config: SsmChaincodeConfig): [ssm.chaincode.dsl.query.SsmGetAdminFunction]
 * - fun ssmGetQueryFunction(config: SsmChaincodeConfig): [ssm.chaincode.dsl.query.SsmGetQueryFunction]
 * - fun ssmGetSessionLogsQueryFunction(config: SsmChaincodeConfig): [ssm.chaincode.dsl.query.SsmGetSessionLogsQueryFunction]
 * - fun ssmGetSessionQueryFunction(config: SsmChaincodeConfig): [ssm.chaincode.dsl.query.SsmGetSessionQueryFunction]
 * - fun ssmGetTransactionQueryFunction(config: SsmChaincodeConfig): [ssm.chaincode.dsl.query.SsmGetTransactionQueryFunction]
 * - fun ssmGetUserFunction(config: SsmChaincodeConfig): [ssm.chaincode.dsl.query.SsmGetUserFunction]
 * - fun ssmListAdminQueryFunction(config: SsmChaincodeConfig): [ssm.chaincode.dsl.query.SsmListAdminQueryFunction]
 * - fun ssmListSessionQueryFunction(config: SsmChaincodeConfig): [ssm.chaincode.dsl.query.SsmListSessionQueryFunction]
 * - fun ssmListSsmQueryFunction(config: SsmChaincodeConfig): [ssm.chaincode.dsl.query.SsmListSsmQueryFunction]
 * - fun ssmListUserQueryFunction(config: SsmChaincodeConfig): [ssm.chaincode.dsl.query.SsmListUserQueryFunction]
 * @d2 model
 * @title Query function
 * @parent [ssm.chaincode.dsl.SsmChaincodeD2]
 */
interface SsmChaincodeQueries {
	fun ssmGetAdminFunction(config: SsmChaincodeConfig): SsmGetAdminFunction
	fun ssmGetQueryFunction(config: SsmChaincodeConfig): SsmGetQueryFunction
	fun ssmGetSessionLogsQueryFunction(config: SsmChaincodeConfig): SsmGetSessionLogsQueryFunction
	fun ssmGetSessionQueryFunction(config: SsmChaincodeConfig): SsmGetSessionQueryFunction
	fun ssmGetTransactionQueryFunction(config: SsmChaincodeConfig): SsmGetTransactionQueryFunction
	fun ssmGetUserFunction(config: SsmChaincodeConfig): SsmGetUserFunction
	fun ssmListAdminQueryFunction(config: SsmChaincodeConfig): SsmListAdminQueryFunction
	fun ssmListSessionQueryFunction(config: SsmChaincodeConfig): SsmListSessionQueryFunction
	fun ssmListSsmQueryFunction(config: SsmChaincodeConfig): SsmListSsmQueryFunction
	fun ssmListUserQueryFunction(config: SsmChaincodeConfig): SsmListUserQueryFunction
}
