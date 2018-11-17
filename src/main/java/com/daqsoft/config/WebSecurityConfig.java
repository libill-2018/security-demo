package com.daqsoft.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * security配置类
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-9-25 15:49
 * @since JDK 1.8
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsServiceImpl myUserDetailsService;
    private final MySecurityInterceptor mySecurityInterceptor;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(MyUserDetailsServiceImpl myUserDetailsService,
                             MySecurityInterceptor mySecurityInterceptor, PasswordEncoder passwordEncoder) {
        this.myUserDetailsService = myUserDetailsService;
        this.mySecurityInterceptor = mySecurityInterceptor;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        List<AuthenticationProvider> providers = new ArrayList<>();
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        providers.add(daoAuthenticationProvider);
        return new ProviderManager(providers);
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        auth.authenticationProvider(daoAuthenticationProvider);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/user/save").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll().successForwardUrl("/index").failureUrl("/login-error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and()
                .addFilterAfter(mySecurityInterceptor, FilterSecurityInterceptor.class);
    }

}
