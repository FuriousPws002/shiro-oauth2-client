package com.furious.oauth2.client.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.pac4j.core.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.furious.oauth2.client.oauth.FuriousClient;
import com.furious.oauth2.client.properties.AuthConfig;

import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.realm.Pac4jRealm;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ShiroConfig extends ShiroWebFilterConfiguration {

    private final AuthConfig config;

    @Override
    protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = super.shiroFilterFactoryBean();
        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(new Config(furiousClient()));
        shiroFilterFactoryBean.getFilters().put("callbackFilter", callbackFilter);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/callback", "callbackFilter");
        map.put("/login**", "anon");
        map.put("/sign**", "anon");
        map.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    @Bean
    public FuriousClient furiousClient() {
        return new FuriousClient(config.getConfig());
    }

    @Bean
    public Realm realm() {
        return new ShiroRealm();
    }

    @Bean
    public Realm pac4jRealm() {
        //应重写pac4jRealm#doGetAuthenticationInfo，获取用户信息
        return new Pac4jRealm();
    }

}
