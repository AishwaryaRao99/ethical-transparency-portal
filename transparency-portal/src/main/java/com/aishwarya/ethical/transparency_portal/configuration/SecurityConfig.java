package com.aishwarya.ethical.transparency_portal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/productsapi/**").permitAll() 
						
						.requestMatchers("/auth/**", "/api/v1/productsapi/**").permitAll()

						.requestMatchers("/admin/**").hasRole("ADMIN")

						.requestMatchers("/client/**").hasRole("CLIENT")

						.requestMatchers("/user/**").hasRole("USER")
						
						.anyRequest().authenticated()
						)
				.httpBasic(Customizer.withDefaults()); // basic authentication for testing purposes, can be replaced
														// with JWT or OAuth2 in production

		return http.build();
	}

}
