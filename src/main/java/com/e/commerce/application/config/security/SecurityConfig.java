package com.e.commerce.application.config.security;

import com.e.commerce.application.domain.services.custom.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtFilter jwtFilter;
    private static final String SYSTEM = "SYSTEM";
    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    /**
     * Security configuration
     * @param httpSecurity - input custom config you want to config
     * @return - security build
     * @throws Exception - error security
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("api/v1/system/**").hasRole(SYSTEM)
                        .requestMatchers("api/v1/admin/**").hasAnyRole(SYSTEM, ADMIN)
                         .requestMatchers(("api/v1/categories")).permitAll()
//                        .requestMatchers("api/v1/user/**").hasAnyRole(ADMIN, USER)
                        .requestMatchers("api/v1/create-default/roles").permitAll()
                        .requestMatchers("api/v1/e-commerce/create-system").permitAll()
                        .requestMatchers("api/v1/register").permitAll()
                        .requestMatchers("api/v1/login").permitAll()
                        .anyRequest().authenticated()
//                        .requestMatchers("api/v1/send-mail").permitAll()
//                        .requestMatchers("api/v1/reset-pw").permitAll()
                        // Config if you need
                )
                .authenticationManager(authenticationManager())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
