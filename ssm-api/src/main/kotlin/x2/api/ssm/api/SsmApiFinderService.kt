package x2.api.ssm.api

import f2.dsl.function.F2Function
import f2.function.spring.adapter.f2Function
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class SsmApiFinderService(
) {

	@Bean
	fun test(): F2Function<Unit, String> = f2Function {
		println("test")
		"test"
	}
}
