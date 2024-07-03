package com.kailash.acuity.config;

import com.kailash.acuity.service.UserService;
import com.kailash.acuity.utils.jwt.AuthEntryPointJwt;
import com.kailash.acuity.utils.jwt.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private static final String[] WHITE_LIST_URL = {
    "/api/v1/auth/**",
    "/v2/api-docs",
    "/v3/api-docs",
    "/v3/api-docs/**",
    "/swagger-resources",
    "/swagger-resources/**",
    "/configuration/ui",
    "/configuration/security",
    "/swagger-ui/**",
    "/webjars/**",
    "/swagger-ui.html",
  };

  private AuthEntryPointJwt authEntryPointJwt;
  private UserService userService;

  SecurityConfig(AuthEntryPointJwt authEntryPointJwt, UserService userService) {
    this.authEntryPointJwt = authEntryPointJwt;
    this.userService = userService;
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  UserDetailsService userDetailsService() {
    return username -> userService.getUserForAuthByUsernameOrEmail(username);
  }

  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  SecurityFilterChain defauSecurityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin))
      .exceptionHandling(exception ->
        exception.authenticationEntryPoint(authEntryPointJwt)
      )
      .authorizeHttpRequests(requests ->
        requests
          .requestMatchers(WHITE_LIST_URL)
          .permitAll()
          .anyRequest()
          .authenticated()
      );

    http.addFilterBefore(
      authenticationJwtTokenFilter(),
      UsernamePasswordAuthenticationFilter.class
    );
    return http.build();
  }

  @Bean
  AuthenticationManager authenticationManager(
    AuthenticationConfiguration builder
  ) throws Exception {
    return builder.getAuthenticationManager();
  }
}
