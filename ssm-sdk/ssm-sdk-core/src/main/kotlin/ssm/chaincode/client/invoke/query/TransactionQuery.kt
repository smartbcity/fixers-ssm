package ssm.chaincode.client.invoke.query

import ssm.chaincode.client.invoke.builder.QueryBuilder
import ssm.chaincode.client.invoke.builder.HasGet
import ssm.chaincode.client.model.SsmQueryName

class TransactionQuery : QueryBuilder(SsmQueryName.TRANSACTION), HasGet
