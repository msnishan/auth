package com.msnishan.auth.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 1)
@EnableResourceServer
public class ResourceServerOAuth2Config extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        .requestMatchers().antMatchers("/auth/users/**")
        .and()
        .authorizeRequests()
        .antMatchers("/auth/users").access("#oauth2.hasScope('read') and hasRole('ROLE_ADMIN')")
        .antMatchers("/auth/users/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("rest-api-user");
        super.configure(resources);
    }
}
