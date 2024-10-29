	package com.deluxe.config;
	
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.http.HttpMethod;
	import org.springframework.security.authentication.AuthenticationProvider;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.config.http.SessionCreationPolicy;
	import org.springframework.security.web.SecurityFilterChain;
	import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
	
	import com.deluxe.jwt.JwtAuthenticationFilter;
	
	import lombok.RequiredArgsConstructor;
	
	@Configuration
	@EnableWebSecurity
	@RequiredArgsConstructor
	public class WebSecurityConfig {
	
		private final JwtAuthenticationFilter jwtAuthenticationFilter;
		private final AuthenticationProvider authProvider;
	    /**
	     * Creación de filtro de rutas privadas y públicas
	     * @param http
	     * @return
	     * @throws Exception
	     */
	    @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			return http
					.csrf(csrf -> csrf
						.disable()
					)
					.authorizeHttpRequests(authRequest -> authRequest
						.requestMatchers("/auth/**","cursos/**","files/**").permitAll()
						.anyRequest().authenticated()
					)
					.sessionManagement(sessionManager ->sessionManager
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.authenticationProvider(authProvider)
					.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
					.build();
			
		}
	}
