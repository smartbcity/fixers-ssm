package ssm.tx.autoconfiguration

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(SsmConfigProperties::class)
@Configuration
class TxSsmAutoconfiguration
