package com.furious.oauth2.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.furious.oauth2.client.oauth.FuriousProperties;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("pac4j")
public class AuthConfig {

    private FuriousProperties config;

//    private String authServer;
//    private String key;
//    private String secret;
}
