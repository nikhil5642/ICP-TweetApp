package com.example.tweetandroidapp;

import org.ic4j.agent.Agent;
import org.ic4j.agent.AgentBuilder;
import org.ic4j.agent.ProxyBuilder;
import org.ic4j.agent.ReplicaTransport;
import org.ic4j.agent.identity.AnonymousIdentity;
import org.ic4j.agent.http.ReplicaOkHttpTransport;
import org.ic4j.types.Principal;

import java.net.URI;

public class ICClient {
    private static ICClient instance;
    private final TweetCanisterService tweetService;

    private ICClient(String canisterId, String icEndpoint) throws Exception {
        // Initialize the agent with an anonymous identity for now
        Agent agent = new AgentBuilder()
                .identity(new AnonymousIdentity())
                .transport(ReplicaOkHttpTransport.create(icEndpoint))
                .build();

        // Use ProxyBuilder to create a proxy for the TweetCanisterService interface
        tweetService = ProxyBuilder.create(agent, Principal.fromString(canisterId))
                .getProxy(TweetCanisterService.class);
    }

    // Singleton instance method
    public static ICClient getInstance(String canisterId, String icEndpoint) throws Exception {
        if (instance == null) {
            instance = new ICClient(canisterId, icEndpoint);
        }
        return instance;
    }

    public TweetCanisterService getTweetService() {
        return tweetService;
    }
}
