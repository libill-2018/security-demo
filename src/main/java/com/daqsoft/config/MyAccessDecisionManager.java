package com.daqsoft.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 决策访问控制
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-11-17 9:07
 * @since JDK 1.8
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    private final MyUserDetailsServiceImpl userDetailsService;

    public MyAccessDecisionManager(MyUserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // 当前登录用户所拥有的权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.isEmpty()) {
            throw new AccessDeniedException("权限不足");
        } else {
            List<String> userAuthorities = authorities.stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            List<String> configAuthorities = configAttributes.stream().map(ConfigAttribute::getAttribute)
                    .collect(Collectors.toList());
            // 登录用户可能会有多种权限，请求的资源也可以允许多种权限来访问，所以取用户权限和资源所需权限的交集
            userAuthorities.retainAll(configAuthorities);
            // 如果交集为空则证明没有访问权限
            if (userAuthorities.isEmpty()) {
                throw new AccessDeniedException("权限不足");
            }
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
