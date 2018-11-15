package com.daqsoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public WebSecurityConfig(MyUserDetailsServiceImpl myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        List<AuthenticationProvider> providers = new ArrayList<>();
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(encoder());
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
                    .anyRequest().hasRole("USER")
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers("/user/**").permitAll()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll().successForwardUrl("/index").failureUrl("/login-error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

}
