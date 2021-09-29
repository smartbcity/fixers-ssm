package ssm.data.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ssm.data.dsl.config.DataSsmConfig

@ConfigurationProperties(prefix = "ssm")
@ConstructorBinding
data class DataSsmProperties(
	val data: DataSsmConfig
)