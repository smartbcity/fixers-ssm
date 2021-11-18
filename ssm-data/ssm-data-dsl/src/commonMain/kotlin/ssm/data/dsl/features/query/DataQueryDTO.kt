package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.uri.SsmUri

expect interface DataQueryDTO {
	/**
	 * Uri information to access a ssm
	 * @example "ssm:channel:chaincode:ssmName"
	 */
	val ssmUri: SsmUri
}
