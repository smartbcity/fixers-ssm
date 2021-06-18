package ssm.chaincode.f2

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.test.context.junit.jupiter.SpringExtension

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension::class)
@SpringBootTest
class SsmSessionStartFunctionImplTest {

	@Autowired
	lateinit var catalog: FunctionCatalog

	@Test
	fun checkFuntionIsRegistred() {
		val initF2: Any = catalog.lookup("ssmSessionStartFunction")
		Assertions.assertThat(initF2).isNotNull
	}
}