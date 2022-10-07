package com.hoangit3.resourceserver.config;

import com.hoangit3.resourceserver.security.CustomAccessTokenConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "microservice";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('READ')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('WRITE')";
    private static final String SECURED_PATTERN = "/**";

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        return converter;
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).disable()
                .authorizeRequests()
                .and()
                .requestMatchers().antMatchers(SECURED_PATTERN)
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, SECURED_PATTERN)
                .access(SECURED_WRITE_SCOPE).anyRequest()
                .access(SECURED_READ_SCOPE);
    }

    @Bean
    public ResourceServerTokenServices tokenService() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId("mobile");
        tokenServices.setClientSecret("pin");
        tokenServices.setAccessTokenConverter(jwtAccessTokenConverter());
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8091/oauth/check_token");
        return tokenServices;
    }
}
