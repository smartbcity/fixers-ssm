package ssm.tx.dsl.features.query

expect interface TxQueryDTO {
	/**
	 * Name of the SSM to query
	 * @example [ssm.chaincode.dsl.Ssm.name]
	 */
	val ssm: SsmName

	/**
	 * Authentication token
	 * @example [ssm.chaincode.dsl.SsmCommandDTO.bearerToken]
	 */
	val bearerToken: String?
}

typealias SsmName = String
