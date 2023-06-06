package ru.home.proj.tasklist.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.home.proj.tasklist.config.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppConf {

    private final ApplicationContext appContext;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> {})
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(
                            (request, response, authException) -> {
                                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                response.getWriter().write("UNAUTHORIZED");
                            });
                    httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(
                            (request, response, accessDeniedException) -> {
                                response.setStatus(HttpStatus.FORBIDDEN.value());
                                response.getWriter().write("FORBIDEN");
                            }
                    );
                })
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry.requestMatchers("api/v1/auth/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                })
                .anonymous(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
