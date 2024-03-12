package com.miu.pmtbackendapi.config.securityconfig;

import com.miu.pmtbackendapi.filter.JwtFilter;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.COOKIES;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    String[] roles = {"ADMIN", "OWNER", "CUSTOMER", "VIEWER"};

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsSvc() {
        return userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .authorizeHttpRequests()
//                .requestMatchers("/api/v1/hello/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/**").permitAll()
//                .requestMatchers("/api/v1/authenticate/**", "/api/v1/users/forgotpassword").permitAll() //for testing reactapp only
//                .requestMatchers("/api/v1/users/forgotpassword/admin").hasAnyAuthority("ADMIN") //for testing reactapp only
//                .requestMatchers("/users/**").hasAnyAuthority("ADMIN") //for testing reactapp only
//                .requestMatchers("/users/**").permitAll() //for testing reactapp only
//                .requestMatchers("/authenticate/**").permitAll()
//                .requestMatchers(HttpMethod.POST, "/token/refresh/").permitAll()
//                .requestMatchers("/admin").hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.logout(logout -> logout.logoutUrl("/api/v1/authenticate/logout")
                .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(COOKIES))) //delete this line if its giving issue
                .deleteCookies("refreshToken").
                addLogoutHandler((request, response, auth) -> {
                    try {
                        request.logout();
                    } catch (ServletException e) {
                    }
                }));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsSvc());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}