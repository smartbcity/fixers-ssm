package ssm.tx.dsl.features.query

expect interface TxQueryDTO {
    val ssm: SsmName
    val bearerToken: String?
}

typealias SsmName = String