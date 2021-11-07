package ssm.sdk.client.invoke.query

import ssm.sdk.client.invoke.builder.HasGet
import ssm.sdk.client.invoke.builder.QueryBuilder

class LogQuery : QueryBuilder(SsmQueryName.LOG), HasGet
