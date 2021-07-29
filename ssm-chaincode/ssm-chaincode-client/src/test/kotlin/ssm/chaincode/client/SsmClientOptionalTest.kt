package ssm.chaincode.client

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import ssm.chaincode.client.SsmClient
import java.util.*

class SsmClientOptionalTest {
    @get:Throws(Exception::class)
    @get:Test
    val adminUser: Unit
        get() {
            val agentRet = client!!.getAdmin(UUID.randomUUID().toString())
            val agentFormClient = agentRet.get()
            Assertions.assertThat(agentFormClient).isNotNull
        }

    @get:Throws(Exception::class)
    @get:Test
    val agentUser2: Unit
        get() {
            val agentRet = client!!.getAgent(UUID.randomUUID().toString())
            val agentFormClient = agentRet.get()
            Assertions.assertThat(agentFormClient).isNotNull
        }

    @get:Throws(Exception::class)
    @get:Test
    val ssm: Unit
        get() {
            val ssmReq = client!!.getSsm(UUID.randomUUID().toString())
            val ssm = ssmReq.get()
            Assertions.assertThat(ssm).isNotNull
        }

    @get:Throws(Exception::class)
    @get:Test
    val session: Unit
        get() {
            val ses = client!!.getSession(UUID.randomUUID().toString())
            val sesReq = ses.get()
            Assertions.assertThat(sesReq).isNotNull
        }

    companion object {
        private var client: SsmClient? = null
        @BeforeAll
        @Throws(Exception::class)
        fun init() {
            client = SsmClientTestBuilder.build()
        }
    }
}