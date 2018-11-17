package com.daqsoft.config;

import com.daqsoft.entity.Operate;
import com.daqsoft.entity.Role;
import com.daqsoft.service.OperateService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 简单描述一下功能
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-11-16 14:05
 * @since JDK 1.8
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final OperateService operateService;

    public MySecurityMetadataSource(OperateService operateService) {
        this.operateService = operateService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        // 系统运行之初就将操作和权限加载到ALL_AUTHORITIES中，避免每次判断都需要从数据库中查询

        return AccessAuthorities.ALL_AUTHORITIES.get(url);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
