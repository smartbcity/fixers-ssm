package ssm.client;


import org.junit.jupiter.api.*;
import ssm.dsl.SsmAgent;
import ssm.dsl.SsmSessionState;
import ssm.dsl.Ssm;

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
        CompletableFuture<Optional<SsmAgent>> agentRet = client.getAdmin(UUID.randomUUID().toString());
        Optional<SsmAgent> agentFormClient = agentRet.get();
        assertThat(agentFormClient).isEmpty();
    }

    @Test
    public void getAgentUser2() throws Exception {
        CompletableFuture<Optional<SsmAgent>> agentRet = client.getAgent(UUID.randomUUID().toString());
        Optional<SsmAgent> agentFormClient = agentRet.get();
        assertThat(agentFormClient).isEmpty();
    }

    @Test
    public void getSsm() throws Exception {
        CompletableFuture<Optional<Ssm>> ssmReq = client.getSsm(UUID.randomUUID().toString());
        Optional<Ssm> ssm = ssmReq.get();
        assertThat(ssm).isEmpty();
    }

    @Test
    public void getSession() throws Exception {
        CompletableFuture<Optional<SsmSessionState>> ses = client.getSession(UUID.randomUUID().toString());
        Optional<SsmSessionState> sesReq = ses.get();
        assertThat(sesReq).isEmpty();
    }

}