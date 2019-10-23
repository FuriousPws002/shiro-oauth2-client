package com.furious.oauth2.client.oauth;

import static org.pac4j.core.profile.AttributeLocation.PROFILE_ATTRIBUTE;

import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.definition.OAuth20ProfileDefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;

//@RequiredArgsConstructor
public class FuriousProfileDefinition extends OAuth20ProfileDefinition<FuriousProfile, OAuth20Configuration> {

    private final String authServer;

    public FuriousProfileDefinition(String authServer){
        super(x -> new FuriousProfile());
        this.authServer = authServer;
    }

    @Override
    public String getProfileUrl(OAuth2AccessToken accessToken, OAuth20Configuration configuration) {
        return authServer + "/user";
    }

    @Override
    public FuriousProfile extractUserProfile(String body) {
        final FuriousProfile profile = newProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
//            profile.setId(ProfileHelper.sanitizeIdentifier(profile, JsonHelper.getElement(json, "id")));
            for (final String attribute : getPrimaryAttributes()) {
                convertAndAdd(profile, PROFILE_ATTRIBUTE, attribute, JsonHelper.getElement(json, attribute));
            }
        } else {
            raiseProfileExtractionJsonError(body);
        }
        return profile;
    }
}
