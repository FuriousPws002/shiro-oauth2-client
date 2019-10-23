package com.furious.oauth2.client.oauth;

import com.github.scribejava.core.builder.api.DefaultApi20;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FuriousApi extends DefaultApi20 {

    private final String authServer;

    @Override
    public String getAccessTokenEndpoint() {
        return authServer + "/oauth/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return authServer + "/oauth/authorize";
    }
}
