package com.project.banking.security;

import com.project.banking.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public JWTAuthorizationFilter jwtAuthorizationFilter(){
        return new JWTAuthorizationFilter();
    }

    @Bean
    public Pbkdf2PasswordEncoder pbkdf2PasswordEncoder() {
        Pbkdf2PasswordEncoder pbkdf2 = new Pbkdf2PasswordEncoder("pepper", 24, 10000, 256);
        pbkdf2.setAlgorithm(Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);
        pbkdf2.setEncodeHashAsBase64(true);
        return pbkdf2;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.cors().and().csrf().disable().authorizeRequests()
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.GET, "/api/user").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/atm").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/bank").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/transaction/withdrawMoney").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/transaction/depositMoney").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/transaction/balanceInquiry").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/transaction/depositMoneyWithPaycheck").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/transaction/withdrawMoney").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api//transaction/monetaryInvestment").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api//transaction/rechargeAtm").hasRole("ADMIN")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))))
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return source;
//    }
}
