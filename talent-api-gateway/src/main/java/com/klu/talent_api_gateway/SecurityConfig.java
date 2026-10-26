package com.klu.talent_api_gateway;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;

import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String secret;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {

        return http

                .csrf(csrf -> csrf.disable())

                .authorizeExchange(exchange -> exchange

                        .pathMatchers(
                                "/actuator/health",
                                "/auth/**"
                        ).permitAll()

                        .anyExchange().authenticated()
                )

                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(
                                jwt -> {}
                        )
                )

                .build();
    }


    @Bean
    public ReactiveJwtDecoder jwtDecoder() {

        SecretKeySpec key =
                new SecretKeySpec(
                        secret.getBytes(),
                        "HmacSHA256"
                );

        return NimbusReactiveJwtDecoder
                .withSecretKey(key)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }
}