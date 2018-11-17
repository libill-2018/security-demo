package com.daqsoft.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义访问过滤器
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-11-16 14:53
 * @since JDK 1.8
 */
@Component
public class MySecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private final FilterInvocationSecurityMetadataSource mySecurityMetadataSource;

    public MySecurityInterceptor(FilterInvocationSecurityMetadataSource mySecurityMetadataSource,
                                 AccessDecisionManager myAccessDecision) {
        this.mySecurityMetadataSource = mySecurityMetadataSource;
        super.setAccessDecisionManager(myAccessDecision);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse, filterChain);
        InterceptorStatusToken interceptorStatusToken = super.beforeInvocation(filterInvocation);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<FilterInvocation> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return mySecurityMetadataSource;
    }
}
