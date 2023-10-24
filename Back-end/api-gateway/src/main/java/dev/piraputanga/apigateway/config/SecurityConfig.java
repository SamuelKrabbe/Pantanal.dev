package dev.piraputanga.apigateway.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

        @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
        private String jwkSetUri;

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.applyPermitDefaultValues();
                configuration.setAllowCredentials(true);
                configuration.addAllowedMethod("GET");
                configuration.addAllowedMethod("POST");
                configuration.addAllowedMethod("DELETE");
                configuration.addAllowedMethod("OPTIONS");
                // configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
                // Configurar ip do frontend
                configuration.setAllowedHeaders(
                                Arrays.asList("Authorization", "Requestor-Type", "Cache-Control", "Content-Type"));
                configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        @Bean
        public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {

                serverHttpSecurity.cors(Customizer.withDefaults());

                serverHttpSecurity.authorizeExchange(exchange -> exchange.pathMatchers("/eureka/**")
                                .permitAll()
                                .anyExchange()
                                .authenticated());

                serverHttpSecurity.oauth2ResourceServer(server -> server.jwt(jwt -> jwt.jwkSetUri(jwkSetUri)));
                return serverHttpSecurity.build();
        }
}
