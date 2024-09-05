package com.ilyaselmabrouki.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private ClientRegistrationRepository clientRegistrationRepository;

    public SecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(ar->ar.requestMatchers("/").permitAll())
                .authorizeHttpRequests(ar-> ar.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .logout((logout) -> logout
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                        .logoutSuccessUrl("/api/v1/auth/").permitAll()
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID"))
                .build();
    }

    private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        final OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}?logoutsuccess=true");
        return oidcLogoutSuccessHandler;
    }
}
