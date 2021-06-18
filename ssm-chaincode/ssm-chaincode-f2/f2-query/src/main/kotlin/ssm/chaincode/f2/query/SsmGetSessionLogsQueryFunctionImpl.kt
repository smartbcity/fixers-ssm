package ssm.chaincode.f2.query

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryResult
import ssm.chaincode.f2.commons.awaitInstances
import ssm.chaincode.f2.commons.ssmF2Function

@Configuration
class SsmGetSessionLogsQueryFunctionImpl {

    @Bean
    fun ssmGetSessionLogsQueryFunction(): SsmGetSessionLogsQueryFunction = ssmF2Function { cmd, ssmClient ->
        ssmClient.log(cmd.session)
            .awaitInstances()
            .let(::SsmGetSessionLogsQueryResult)
    }
}