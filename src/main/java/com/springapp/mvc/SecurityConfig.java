package com.springapp.mvc;

/**
 * Created by metairie on 02-Sep-15.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private UserDetailsService neosUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().
//                antMatchers("/**").access("hasRole('ADMIN')").
//                and().formLogin();
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("root").password("eurovision").roles("ADMIN");
    }

//    @Configuration
//    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication().withUser("ravan").password("ravan123").roles("USER");
//            auth.inMemoryAuthentication().withUser("ram").password("ram123").roles("ADMIN");
//        }
//    }
}