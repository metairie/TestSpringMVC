package com.springapp.mvc;

/**
 * Created by metairie on 02-Sep-15.
 * <p/>
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//      DOES NOT WORK
//      @Autowired
//      public UserDetailsService neosUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // for having https mandatory
                // .requiresChannel().anyRequest().requiresSecure()
                // .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**")
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()

        //.and()
        //.authorizeRequests()
        //.antMatchers("/admin/**").access("hasRole('ADMIN')")
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//      OK works
        // return the role according user account
        // to be fully complete
        // implement class Account (real world)
        // this class could retrieve JDBC data
//        auth.userDetailsService(new NeosUserDetailsService());

//      OK works
        auth.inMemoryAuthentication().withUser("root").password("").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("agent").password("").roles("AGENT");
    }
}