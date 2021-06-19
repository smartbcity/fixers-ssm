package ssm.tx.autoconfiguration

import org.springframework.boot.context.properties.ConstructorBinding
import ssm.tx.dsl.config.TxSsmConfig
import ssm.tx.dsl.config.TxSsmLocationProperties
import ssm.tx.dsl.features.query.SsmName
import ssm.tx.dsl.model.SsmVersion


@ConstructorBinding
class SsmConfigProperties(
	val ssm: TxSsmConfig
)