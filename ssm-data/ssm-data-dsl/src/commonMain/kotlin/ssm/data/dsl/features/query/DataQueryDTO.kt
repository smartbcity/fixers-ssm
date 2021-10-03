package ssm.data.dsl.features.query

expect interface DataQueryDTO {
	/**
	 * Name of the SSM to query
	 * @example [ssm.chaincode.dsl.model.Ssm.name]
	 */
	val ssm: SsmName

	/**
	 * Authentication token
	 * @example [ssm.chaincode.dsl.SsmCommandDTO.bearerToken]
	 */
	val bearerToken: String?
}

typealias SsmName = String
