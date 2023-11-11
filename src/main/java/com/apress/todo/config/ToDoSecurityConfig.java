package com.apress.todo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ToDoSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
            .username("apress")
            .password(passwordEncoder().encode("springboot2"))
            .roles("USER", "ADMIN")
            .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security
            .authorizeHttpRequests(requests -> requests
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()
                .anyRequest()
                .fullyAuthenticated()
            )
            .formLogin(loginConfigurer -> loginConfigurer
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
            )
            .httpBasic(withDefaults())
            .build();
    }
}
