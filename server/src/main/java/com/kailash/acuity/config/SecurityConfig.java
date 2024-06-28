package com.kailash.acuity.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  DataSource dataSource;

  // @Bean
  // PasswordEncoder passwordEncoder() {
  //   return new BCryptPasswordEncoder();
  // }

  @Bean
  UserDetailsService userDetailsService() {
    UserBuilder user1 = User
      .withUsername("user")
      .password("{noop}user")
      .roles("USER");
    UserBuilder admin = User
      .withUsername("admin")
      .password("{noop}admin")
      .roles("ADMIN");
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(
      dataSource
    );
    jdbcUserDetailsManager.createUser(user1.build());
    jdbcUserDetailsManager.createUser(admin.build());

    // InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1.build(), admin.build());

    return jdbcUserDetailsManager;
  }

  @Bean
  SecurityFilterChain defauSecurityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .authorizeHttpRequests(requests ->
        requests
          .requestMatchers("/api/v1/auth/**")
          .permitAll()
          .anyRequest()
          .authenticated()
      );
    http.httpBasic(withDefaults());
    return http.build();
  }
}
