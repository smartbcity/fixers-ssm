package ssm.test.cucumber.spring

import io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
class BagF2Catalog(
	val catalog: FunctionCatalog,
)
