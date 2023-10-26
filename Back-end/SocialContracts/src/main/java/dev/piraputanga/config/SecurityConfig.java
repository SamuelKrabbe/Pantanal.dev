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
                                .requestMatchers(new AntPathRequestMatcher("/socialcontracts", "POST"))
                                .hasAuthority(KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "admin")
                                .requestMatchers(new AntPathRequestMatcher("/socialcontracts", "DELETE"))
                                .hasAuthority(KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "admin")
                                .requestMatchers(new AntPathRequestMatcher("/socialcontracts", "PUT"))
                                .hasAuthority(KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "admin")
                                .requestMatchers(new AntPathRequestMatcher("/socialcontracts", "GET"))
                                .hasAuthority(KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "admin")
                                .requestMatchers(new AntPathRequestMatcher("/socialcontracts/usersocialcontracts", "GET"))
                                .hasAnyAuthority(KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "admin", KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "user")
                                .requestMatchers(new AntPathRequestMatcher("/docs/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll()
                                .anyRequest().authenticated());

                return http.build();
        }

}