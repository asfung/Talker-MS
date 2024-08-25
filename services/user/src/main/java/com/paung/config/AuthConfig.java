package com.paung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {
  @Bean
  public UserDetailsService userDetailsService(){
    return new CustomUserDetailsService();
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // DEPRECATED
//    return http.csrf().disable()
//            .authorizeHttpRequests()
//            .requestMatchers("/auth/register", "/auth/token", "/auth/validate").permitAll()
//            .and()
//            .build();
//    http.csrf(csrf -> csrf.disable());

    http.csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests((authorize) -> {
              authorize.requestMatchers(
                      "/api/v1/auth/register",
                              "/api/v1/auth/token",
                              "/api/v1/auth/validate",
                              "/api/v1/auth",
                              "/api/v1/user/**"
                      )
              .permitAll();
            });
    return http.build();

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
