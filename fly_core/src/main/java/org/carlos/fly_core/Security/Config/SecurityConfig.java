package org.carlos.fly_core.Security.Config;

import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.Security.Filters.JWTauthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTauthFilter JWTauthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(
                AbstractHttpConfigurer::disable
        ).authorizeHttpRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers("/ws/info/**").permitAll()
                        .anyRequest().permitAll()

        ).sessionManagement(
                session -> session.sessionCreationPolicy(STATELESS)
        ).authenticationProvider(
                authenticationProvider
        ).addFilterBefore(JWTauthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
