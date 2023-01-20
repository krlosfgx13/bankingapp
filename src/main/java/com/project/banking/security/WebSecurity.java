package com.project.banking.security;

import com.project.banking.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;

    private Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;

    public WebSecurity(UserDetailsServiceImpl userDetailsService, Pbkdf2PasswordEncoder pbkdf2PasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.pbkdf2PasswordEncoder = pbkdf2PasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_IN_URL).permitAll();
                //.antMatchers(HttpMethod.GET, "/api/user").hasAnyRole("USER", "ADMIN")
                //.antMatchers(HttpMethod.GET, "/api/bank").hasRole("ADMIN").and()
                //.and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
                //.and().addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService));
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(pbkdf2PasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
