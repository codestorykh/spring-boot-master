package com.rean.springbootmaster.user.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rean.springbootmaster.user.exception.CustomAccessDeniedHandler;
import com.rean.springbootmaster.user.filter.CustomAuthenticationProvider;
import com.rean.springbootmaster.user.filter.JwtAuthenticationFilter;
import com.rean.springbootmaster.user.filter.JwtAuthenticationInternalFilter;
import com.rean.springbootmaster.user.jwt.JwtConfig;
import com.rean.springbootmaster.user.jwt.JwtService;
import com.rean.springbootmaster.user.security.CustomUserDetailService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomSecurityFilterChain extends JwtConfig {

    private final CustomUserDetailService customUserDetailService;
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;
    private final JwtConfig jwtConfig;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void userAuthenticationGlobalConfig(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManagerBuilder managerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = managerBuilder.build();

         httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(auth -> auth
                     .requestMatchers(
                             "api/v1/public/accounts/**",
                             "api/v1/public/login/**",
                             "api/v1/public/refreshToken/**")
                     .permitAll()
                     .requestMatchers("/swagger-ui/**",
                             "/swagger-resources/*",
                             "/v3/api-docs/**",
                             "codestorykh-api-docs/**")
                     .permitAll()
                     .requestMatchers("/api/v1/user/**")
                     .hasAnyAuthority("USER", "ADMIN")
                     .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
                     .anyRequest()
                     .authenticated()
             )
            .authenticationManager(authenticationManager)
                 .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .exceptionHandling(
                         (exception)-> exception
                                 .authenticationEntryPoint(
                                         (((request, response, authException)
                                                 -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))))
                                 .accessDeniedHandler(new CustomAccessDeniedHandler()))
            .addFilterBefore(
                    new JwtAuthenticationFilter(
                    jwtService, objectMapper, jwtConfig, authenticationManager, customUserDetailService),
                    UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new JwtAuthenticationInternalFilter(jwtService, objectMapper, jwtConfig),
                        UsernamePasswordAuthenticationFilter.class);

     return httpSecurity.build();
    }

}