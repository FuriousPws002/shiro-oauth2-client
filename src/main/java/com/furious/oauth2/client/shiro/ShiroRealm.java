package com.furious.oauth2.client.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

//@Component
public class ShiroRealm extends AuthorizingRealm {

    private static final String USERNAME = "u";
    private static final String PASSWORD = "p";
    private static final Collection<String> ROLES = new HashSet<>();
    private static final Collection<String> PERMISSIONS = new HashSet<>();

    static {
        ROLES.add("admin");
        PERMISSIONS.add("system:user:add");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(ROLES);
        authorizationInfo.addStringPermissions(PERMISSIONS);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token instanceof UsernamePasswordToken) {
            UsernamePasswordToken upt = (UsernamePasswordToken) token;
            if (Objects.equals(upt.getPrincipal(), USERNAME) && Objects.equals(PASSWORD, new String(upt.getPassword()))) {
                return new SimpleAuthenticationInfo(upt.getPrincipal(), upt.getCredentials(), getName());
            }
        }
        throw new AuthenticationException("login fail");
    }
}
