package com.example.tweetandroidapp.icp;

import android.content.Context;

import com.example.tweetandroidapp.R;

import org.ic4j.agent.Agent;
import org.ic4j.agent.AgentBuilder;
import org.ic4j.agent.ProxyBuilder;
import org.ic4j.agent.identity.AnonymousIdentity;
import org.ic4j.agent.http.ReplicaOkHttpTransport;
import org.ic4j.types.Principal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class ICClient {
    private static ICClient instance;
    private final TweetCanisterService tweetService;

    private ICClient(String canisterId, String icEndpoint, Reader identity) throws Exception {
        Agent agent = new AgentBuilder()
                .identity(new AnonymousIdentity())
                .transport(ReplicaOkHttpTransport.create(icEndpoint))
                .build();
        // Use ProxyBuilder to create a proxy for the TweetCanisterService interface
        tweetService = ProxyBuilder.create(agent, Principal.fromString(canisterId))
                .getProxy(TweetCanisterService.class);
    }

    // Singleton instance method
    public static ICClient getInstance(Context context) throws Exception {
        if (instance == null) {
            Reader identityReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.identity)));
            instance = new ICClient(
                    context.getString(R.string.canister_id),
                    context.getString(R.string.ic_endpoint),
                    identityReader
            );
        }
        return instance;
    }

    public TweetCanisterService getTweetService() {
        return tweetService;
    }


}
