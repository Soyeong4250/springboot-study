package com.springboot.api.configuration;

import com.springboot.api.jwt.JwtTokenFilter;
import com.springboot.api.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Value("${jwt.token.secret}")
    private String secretKey;
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
//                ignoringAntMatchers("/api/**")
//                .and()
                .cors().and()
                .authorizeHttpRequests()
                .antMatchers("/", "/auth/**", "posts/list", "posts/search").permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtTokenFilter(secretKey, jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
