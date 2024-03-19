package com.minsait.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${security.user}")
    private String user;
    @Value("${security.password}")
    private String password;
    @Value("${gateway.global}")
    private String global;
    @Value("${gateway.videogames}")
    private String videoGames;
    @Value("${gateway.promotion}")
    private String promotion;
    @Value("${gateway.categories}")
    private String categories;
    @Value("${gateway.platforms}")
    private String platforms;
    @Value("${gateway.shop}")
    private String shop;
    @Value("${gateway.stock}")
    private String stock;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
                .authorizeHttpRequests(auth -> auth.requestMatchers(global + videoGames + "/**", global + promotion + "/**",
                        global + categories + "/**",global + platforms + "/**" ,global + shop + "/**", global + stock + "/**")
                        .authenticated())
                .httpBasic(Customizer.withDefaults());
        return security.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        return new InMemoryUserDetailsManager(
                User.withUsername(user)
                        .password(passwordEncoder.encode(password))
                        .build()
        );
    }

}
