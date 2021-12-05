package ssm.data.dsl.features.query

import ssm.chaincode.dsl.model.uri.ChaincodeUri
import ssm.data.dsl.model.DataSsmDTO

@JsExport
@JsName("DataChaincodeListQueryDTO")
actual external interface DataChaincodeListQueryDTO


@JsExport
@JsName("DataChaincodeListQueryResultDTO")
actual external interface DataChaincodeListQueryResultDTO {
	/**
	 * List of all retrieved SSMs
	 */
	actual val items: List<ChaincodeUri>
}
