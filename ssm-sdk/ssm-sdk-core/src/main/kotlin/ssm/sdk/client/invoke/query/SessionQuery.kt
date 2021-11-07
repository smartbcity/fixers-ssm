package ssm.sdk.client.invoke.query

import ssm.sdk.client.invoke.builder.HasGet
import ssm.sdk.client.invoke.builder.HasList
import ssm.sdk.client.invoke.builder.QueryBuilder

class SessionQuery : QueryBuilder(SsmQueryName.SESSION), HasGet, HasList
