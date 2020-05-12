package org.civis.blockchain.ssm.client;

import org.civis.blockchain.ssm.client.command.CreateCommand;
import org.civis.blockchain.ssm.client.command.PerformCommand;
import org.civis.blockchain.ssm.client.command.RegisterCommand;
import org.civis.blockchain.ssm.client.command.StartCommand;
import org.civis.blockchain.ssm.client.domain.*;
import org.civis.blockchain.ssm.client.query.AdminQuery;
import org.civis.blockchain.ssm.client.query.AgentQuery;
import org.civis.blockchain.ssm.client.query.SessionQuery;
import org.civis.blockchain.ssm.client.query.SsmQuery;
import org.civis.blockchain.ssm.client.repository.InvokeReturn;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class SsmClient {

    public static SsmClient fromConfigFile(String filename) throws IOException {
        return SsmClientBuilder.builder(filename).build();
    }

    public static SsmClient fromConfig(SsmClientConfig config) {
        return SsmClientBuilder.builder(config).build();
    }

    private final SsmRequester ssmRequester;

    public SsmClient(SsmRequester ssmRequester) {
        this.ssmRequester = ssmRequester;
    }

    public CompletableFuture<InvokeReturn> registerUser(Signer signer, Agent agent) throws Exception {
        RegisterCommand cmd = new RegisterCommand(signer, agent);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<InvokeReturn> create(Signer signer, Ssm ssm) throws Exception {
        CreateCommand cmd = new CreateCommand(signer, ssm);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<InvokeReturn> start(Signer signer, Session session) throws Exception {
        StartCommand cmd = new StartCommand(signer, session);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<InvokeReturn> perform(Signer signer, String action, Context context) throws Exception {
        PerformCommand cmd = new PerformCommand(signer, action, context);
        return ssmRequester.invoke(cmd);
    }

    public CompletableFuture<List<String>> listAdmins() {
        AdminQuery query = new AdminQuery();
        return ssmRequester.list(query, String.class);
    }

    public CompletableFuture<Optional<Agent>> getAdmin(String username) {
        AdminQuery query = new AdminQuery();
        return ssmRequester.query(username, query, Agent.class);
    }

    public CompletableFuture<List<String>> listAgent() {
        AgentQuery query = new AgentQuery();
        return ssmRequester.list(query, String.class);
    }

    public CompletableFuture<Optional<Agent>> getAgent(String agentName)  {
        AgentQuery query = new AgentQuery();
        return ssmRequester.query(agentName, query, Agent.class);
    }

    public CompletableFuture<List<String>> listSsm() {
        SsmQuery query = new SsmQuery();
        return ssmRequester.list(query, String.class);
    }

    public CompletableFuture<Optional<Ssm>> getSsm(String name) {
        SsmQuery query = new SsmQuery();
        return ssmRequester.query(name, query, Ssm.class);
    }

    public CompletableFuture<Optional<SessionState>> getSession(String sessionId) {
        SessionQuery query = new SessionQuery();
        return ssmRequester.query(sessionId, query, SessionState.class);
    }

    public CompletableFuture<List<String>> listSession() {
        SessionQuery query = new SessionQuery();
        return ssmRequester.list(query, String.class);
    }

}
