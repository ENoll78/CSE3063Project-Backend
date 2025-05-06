package com.teamawesome.backend.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }                                 // turn off CSRF for H2 console
            .headers { it.frameOptions { it.disable() } }           // allow frames to render H2
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/h2-console/**").permitAll()      // open H2 console
                    .anyRequest().authenticated()                       // secure the rest
            }
            .httpBasic(Customizer.withDefaults())                   // enable HTTP Basic

        return http.build()
    }
}
