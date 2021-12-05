package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.data.dsl.model.DataSsmDTO

actual interface DataChaincodeListQueryDTO

actual interface DataChaincodeListQueryResultDTO {
	/**
	 * List of all retrieved SSMs
	 */
	actual val items: List<ChaincodeUri>
}
