package com.example.demo.config;

// SecurityConfig.java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                if ("admin".equals(username)) {
                    return User.withUsername("admin")
                            .password(encoder().encode("admin"))
                            .roles("ADMIN")
                            .build();
                } else if ("user".equals(username)) {
                    return User.withUsername("user")
                            .password(encoder().encode("user"))
                            .roles("USER")
                            .build();
                } else if ("user2".equals(username)) {
                    return User.withUsername("user2")
                            .password(encoder().encode("user2"))
                            .roles("USER")
                            .build();
                } else {
                    throw new UsernameNotFoundException("User not found");
                }
            }
        };
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/users/**").hasRole("USER")
                                .requestMatchers("/api/users/groceries")
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults()).build();
    	

    }
    
   
   
}
