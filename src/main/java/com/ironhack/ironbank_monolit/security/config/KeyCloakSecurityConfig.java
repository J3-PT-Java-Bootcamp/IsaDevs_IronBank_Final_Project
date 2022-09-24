package com.ironhack.ironbank_monolit.security.config;

import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) //jsr ??? what is the reference?
public class KeyCloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    //************************************************************************************
    // THIS OVERRIDE CONFIGURE ALL PERMISSIONS IN KEYCLOAK, THIS CLASS EXTENDS FROM KeycloakWebSecurityConfigurerAdapter
    //************************************************************************************
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        super.configure(http);
        http.authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/swagger-ui.html", "/v2/api-docs", "/webjars/**","/swagger-resources/**").permitAll()
                .antMatchers("/member/**").hasAnyRole("member") //this could be totally customized
                .antMatchers("/user/**").hasAnyRole("user")
                .antMatchers("/admin/**").hasAnyRole("admin") //"/admin/**"
                .anyRequest()
                .permitAll();

        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authority) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider(); // CALL TO METOHOD FROM
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        authority.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
}
