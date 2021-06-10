package ssm.f2.query

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.dsl.query.SsmGetSessionLogsQueryResult
import ssm.f2.commons.awaitInstances
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmGetSessionLogsQueryFunctionImpl {

    @Bean
    fun ssmGetSessionLogsQueryFunction(): SsmGetSessionLogsQueryFunction = ssmF2Function { cmd, ssmClient ->
        ssmClient.log(cmd.session)
            .awaitInstances()
            .let(::SsmGetSessionLogsQueryResult)
    }
}