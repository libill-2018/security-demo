package com.daqsoft.config;

import com.daqsoft.entity.Operate;
import com.daqsoft.service.OperateService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存所有操作以及对应的角色信息
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-11-17 11:16
 * @since JDK 1.8
 */
@Component
public class AccessAuthorities {

    private final OperateService operateService;

    public static final Map<String, List<ConfigAttribute>> ALL_AUTHORITIES = new ConcurrentHashMap<>();

    public AccessAuthorities(OperateService operateService) {
        this.operateService = operateService;
    }

    @PostConstruct
    public void loadAuthorities() {
        List<Operate> all = operateService.findAll();
        all.forEach(operate -> {
            List<ConfigAttribute> attributes = new ArrayList<>();
            operate.getRoles().forEach(role -> {
                ConfigAttribute configAttribute = new SecurityConfig(role.getCode());
                attributes.add(configAttribute);
            });
            ALL_AUTHORITIES.put(operate.getUrl(), attributes);
        });
    }

}
