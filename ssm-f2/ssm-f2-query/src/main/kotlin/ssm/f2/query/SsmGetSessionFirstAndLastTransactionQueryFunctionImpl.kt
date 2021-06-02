package ssm.f2.query

import kotlinx.coroutines.future.await
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.dsl.query.SsmGetSessionFirstAndLastTransactionQueryFunction
import ssm.dsl.query.SsmGetSessionFirstAndLastTransactionQueryResult
import ssm.f2.commons.awaitInstances
import ssm.f2.commons.ssmF2Function

@Configuration
class SsmGetSessionFirstAndLastTransactionQueryFunctionImpl {

    @Bean
    fun ssmGetSessionFirstAndLastTransactionQueryFunction(): SsmGetSessionFirstAndLastTransactionQueryFunction = ssmF2Function { cmd, ssmClient ->
        val sessionLogs = ssmClient.log(cmd.session).awaitInstances()

        if (sessionLogs.isEmpty()) {
            return@ssmF2Function SsmGetSessionFirstAndLastTransactionQueryResult(null, null)
        }

        val firstTransactionId = sessionLogs.minByOrNull { sessionLog -> sessionLog.state.iteration }?.txId
        val lastTransactionId = sessionLogs.maxByOrNull { sessionLog -> sessionLog.state.iteration }?.txId

        val firstTransaction = ssmClient.getTransaction(firstTransactionId)
            .await()
            .orElse(null)

        val lastTransaction = ssmClient.getTransaction(lastTransactionId)
            .await()
            .orElse(null)

        SsmGetSessionFirstAndLastTransactionQueryResult(
            firstTransaction = firstTransaction,
            lastTransaction = lastTransaction
        )
    }
}