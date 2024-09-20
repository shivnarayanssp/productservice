package dev.shiv4u.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /*@Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws  Exception{
           httpSecurity.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/products").hasAuthority("admin")
                        .anyRequest().permitAll()
                )
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(Customizer.withDefaults());
        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }*/
}
