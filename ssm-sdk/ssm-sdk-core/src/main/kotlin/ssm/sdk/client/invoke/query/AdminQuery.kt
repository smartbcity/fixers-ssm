package ssm.sdk.client.invoke.query

import ssm.sdk.client.invoke.builder.HasGet
import ssm.sdk.client.invoke.builder.HasList
import ssm.sdk.client.invoke.builder.QueryBuilder

class AdminQuery : QueryBuilder(SsmQueryName.ADMIN), HasGet, HasList
