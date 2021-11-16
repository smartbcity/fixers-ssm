package ssm.data.dsl.model

import ssm.chaincode.dsl.blockchain.TransactionDTO
import ssm.chaincode.dsl.model.SessionName
import ssm.chaincode.dsl.model.uri.SsmUri

actual interface DataSsmSessionDTO {
	actual val ssmUri: SsmUri
	actual val sessionName: SessionName
	actual val state: DataSsmSessionStateDTO
	actual val channel: DataChannelDTO
	actual val transaction: TransactionDTO?
	actual val transactions: List<TransactionDTO>
}
