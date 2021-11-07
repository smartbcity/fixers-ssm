package ssm.sdk.client.invoke.query

import ssm.sdk.client.invoke.builder.HasGet
import ssm.sdk.client.invoke.builder.QueryBuilder

class BlockQuery : QueryBuilder(SsmQueryName.BLOCK), HasGet
