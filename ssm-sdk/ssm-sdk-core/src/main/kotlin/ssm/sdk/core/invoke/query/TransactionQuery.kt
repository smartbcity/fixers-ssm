package ssm.sdk.core.invoke.query

import ssm.sdk.core.invoke.builder.HasGet
import ssm.sdk.core.invoke.builder.QueryBuilder

class TransactionQuery : QueryBuilder(SsmQueryName.TRANSACTION), HasGet
