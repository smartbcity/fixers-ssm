package x2.api.ssm.api.utils

import SsmVersion
import TxSsmCommand
import f2.dsl.function.F2Function
import f2.function.spring.adapter.f2Function
import org.springframework.context.annotation.Configuration
import x2.api.config.SsmLocationProperties
import x2.api.config.X2SsmConfig

private lateinit var config: X2SsmConfig

@Configuration
private class CdbF2Initializer(
    private val x2SsmConfig: X2SsmConfig
) {
    init {
        config = this.x2SsmConfig
    }
}

fun <T: TxSsmCommand, R> x2F2Function(fnc: suspend (T, Map<SsmVersion, SsmLocationProperties>) -> R): F2Function<T, R> = f2Function { cmd ->
    val ssmConfig = config.ssmMap()[cmd.ssm]
        ?: throw IllegalArgumentException("Configuration of SSM [${cmd.ssm}] not found")

    fnc(cmd, ssmConfig)
}