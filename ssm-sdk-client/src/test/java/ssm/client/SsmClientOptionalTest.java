package ssm.client;


import org.junit.jupiter.api.*;
import ssm.dsl.SsmAgentBase;
import ssm.dsl.SsmSessionStateBase;
import ssm.dsl.SsmBase;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class SsmClientOptionalTest {

    private static SsmClient client;

    @BeforeAll
    public static void init() throws Exception {
        client = SsmClientTestBuilder.build();
    }

    @Test
    public void getAdminUser() throws Exception {
        CompletableFuture<Optional<SsmAgentBase>> agentRet = client.getAdmin(UUID.randomUUID().toString());
        Optional<SsmAgentBase> agentFormClient = agentRet.get();
        assertThat(agentFormClient).isEmpty();
    }

    @Test
    public void getAgentUser2() throws Exception {
        CompletableFuture<Optional<SsmAgentBase>> agentRet = client.getAgent(UUID.randomUUID().toString());
        Optional<SsmAgentBase> agentFormClient = agentRet.get();
        assertThat(agentFormClient).isEmpty();
    }

    @Test
    public void getSsm() throws Exception {
        CompletableFuture<Optional<SsmBase>> ssmReq = client.getSsm(UUID.randomUUID().toString());
        Optional<SsmBase> ssm = ssmReq.get();
        assertThat(ssm).isEmpty();
    }

    @Test
    public void getSession() throws Exception {
        CompletableFuture<Optional<SsmSessionStateBase>> ses = client.getSession(UUID.randomUUID().toString());
        Optional<SsmSessionStateBase> sesReq = ses.get();
        assertThat(sesReq).isEmpty();
    }

}