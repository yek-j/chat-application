package com.yekj.proejct.chatapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationProviderService authenticationProviderService;


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) ->  authz.
                            requestMatchers(HttpMethod.POST, "/users/signup").permitAll()
                            .requestMatchers(HttpMethod.GET, "/users/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/login").permitAll()
                            .anyRequest().authenticated()
                )
                .formLogin((login) -> {
                    login.defaultSuccessUrl("/main", true);
                }).build();
    }

    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProviderService);
        return auth.build();
    }
}
