package com.project.banking;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//This is a web security configuration.
//@Configuration
//@EnableWebSecurity
public class SecurityConfigurationTest extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //set your configuration on the auth object.
        auth.inMemoryAuthentication()
                .withUser("blah")
                .password("blah")
                .roles("USER")
                .and()
                .withUser("antonio")
                .password("1234")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //hasRole for a single role.
        //hasAnyRole for one in a list of roles.
        http.authorizeRequests()
                .antMatchers("/api/bank/**").hasRole("ADMIN")
                .antMatchers("/api/user/**").hasRole("USER")
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //return encoder;
    }
}
