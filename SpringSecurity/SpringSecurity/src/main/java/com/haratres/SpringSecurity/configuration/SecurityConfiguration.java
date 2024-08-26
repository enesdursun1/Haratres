package com.haratres.SpringSecurity.configuration;

import com.haratres.SpringSecurity.core.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {


   @Autowired
   private JwtFilter jwtFilter;
   @Autowired
   UserDetailsService userDetailsService;
   @Autowired
	private PasswordEncoder passwordEncoder;

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
          .csrf(csrf -> csrf.disable())
           .authorizeHttpRequests(authorize -> authorize
               .requestMatchers("/login","/register", "/resources/**").permitAll()
               .requestMatchers("/api/products/**","/api/categories/**","/api/prices/**",
                       "/api/stocks/**","/api/cartProducts/getAll").hasRole("ADMIN")
               .anyRequest().authenticated() 
           )
               .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


       return http.build();
   }



   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
       return authConfig.getAuthenticationManager();
   }

   @Autowired
   public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
   }

   @Bean
   public WebSecurityCustomizer webSecurityCustomizer() {
       return (web) -> web.ignoring().requestMatchers("/resources/**");
   }
}
