package dev.piraputanga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.DelegatingJwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import dev.piraputanga.utils.KeycloakJwtRolesConverter;

@Configuration
@EnableWebSecurity
class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                DelegatingJwtGrantedAuthoritiesConverter authoritiesConverter = new DelegatingJwtGrantedAuthoritiesConverter(
                                // Default converter
                                new JwtGrantedAuthoritiesConverter(),
                                // Custom Keycloak specific converter
                                new KeycloakJwtRolesConverter());

                http.oauth2ResourceServer(server -> server.jwt(jwt -> jwt.jwtAuthenticationConverter(
                                token -> new JwtAuthenticationToken(token, authoritiesConverter.convert(token)))));

                http.authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers(new AntPathRequestMatcher("/socialactions", "POST"))
                                .hasAuthority(KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "admin")
                                .requestMatchers(new AntPathRequestMatcher("/socialactions", "DELETE"))
                                .hasAuthority(KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "admin")
                                .requestMatchers(new AntPathRequestMatcher("/socialactions", "PUT"))
                                .hasAuthority(KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "admin")
                                .requestMatchers(new AntPathRequestMatcher("/socialactions", "GET"))
                                .hasAnyAuthority(KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "admin", KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "user")
                                .anyRequest().authenticated());

                return http.build();
        }

}
