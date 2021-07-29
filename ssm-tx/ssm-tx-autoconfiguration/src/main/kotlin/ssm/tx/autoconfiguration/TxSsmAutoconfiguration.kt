package ssm.tx.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ssm.tx.dsl.config.TxSsmConfig

@Configuration
class TxSsmAutoconfiguration {

    @Bean
    @ConfigurationProperties(prefix = "ssm.list")
    fun ssmMap(): TxSsmConfig {
        return HashMap()
    }
}
