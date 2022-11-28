package com.cifolio.cifolio.config;

import com.cifolio.cifolio.filter.AuthenticationFilter;
import com.cifolio.cifolio.filter.AuthorizationFilter;
import com.cifolio.cifolio.service.token.TokenService;
import com.cifolio.cifolio.service.user.CustomUserDetailsService;
import com.cifolio.cifolio.utils.JWKS_Builder;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static com.cifolio.cifolio.constants.SecurityConstants.JWT_AUTHORITIES_CLAIM_NAME;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfigurations {
    private final CustomUserDetailsService customUserDetailsService;
    private RSAKey rsaKey;
    private final String[] WHITE_LIST = {"/h2-console/**", "/api/register", "/api/login", "/api/logout"};

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authManager(), new TokenService(jwtEncoder(jwkSource())));
        authenticationFilter.setFilterProcessesUrl("/api/login");

        return http
                .csrf().disable().headers().frameOptions().disable() // for viewing h2-console
                .and()
                .cors() // need corsConfigurationSource for this to work.
                .and()
                .authorizeRequests(auth -> auth
                        .antMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .userDetailsService(customUserDetailsService)
                .oauth2ResourceServer(oath2 -> oath2
                    .jwt()
                    .jwtAuthenticationConverter(authenticationConverter()))
                .addFilter(authenticationFilter)
                .addFilterBefore(new AuthorizationFilter(jwtDecoder()), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    JwtAuthenticationConverter authenticationConverter() {
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("");
        authoritiesConverter.setAuthoritiesClaimName(JWT_AUTHORITIES_CLAIM_NAME);

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return converter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000/"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "OPTIONS",
                "DELETE",
                "PUT")
        );
        configuration.setAllowedHeaders(Arrays.asList(
                "Content-Type",
                "content-type",
                "x-requested-with",
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Headers",
                "x-auth-token",
                "x-app-id",
                "Origin",
                "Accept",
                "X-Requested-With",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers")
        );
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        this.rsaKey = JWKS_Builder.generateRsa();
        JWKSet jwkSet = new JWKSet(this.rsaKey);
        return ((jwkSelector, securityContext) -> jwkSelector.select(jwkSet));
    }

    @Bean
    JwtDecoder jwtDecoder() throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwks) {
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
