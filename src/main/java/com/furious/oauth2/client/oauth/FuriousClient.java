package com.furious.oauth2.client.oauth;

import org.pac4j.core.http.url.DefaultUrlResolver;
import org.pac4j.oauth.client.OAuth20Client;


/**
 * @see org.pac4j.oauth.client.GitHubClient
 */
public class FuriousClient extends OAuth20Client<FuriousProfile> {

    private final FuriousProperties properties;

    public FuriousClient(FuriousProperties properties) {
        this.properties = properties;
        setKey(properties.getKey());
        setSecret(properties.getSecret());

        setUrlResolver(new DefaultUrlResolver(true));
//        setCallbackUrl("http://localhost:8082/callback");
        setCallbackUrl("/callback");
    }

    @Override
    protected void clientInit() {
        configuration.setApi(new FuriousApi(properties.getAuthServer()));
        configuration.setProfileDefinition(new FuriousProfileDefinition(properties.getAuthServer()));
        super.clientInit();
    }

}
