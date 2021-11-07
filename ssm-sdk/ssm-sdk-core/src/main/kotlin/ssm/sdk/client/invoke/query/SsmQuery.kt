package ssm.sdk.client.invoke.query

import ssm.sdk.client.invoke.builder.HasGet
import ssm.sdk.client.invoke.builder.HasList
import ssm.sdk.client.invoke.builder.QueryBuilder

class SsmQuery : QueryBuilder(SsmQueryName.SSM), HasGet, HasList
