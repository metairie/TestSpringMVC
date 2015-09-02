package com.springapp.mvc;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by metairie on 01-Sep-15.
 */
@Component
public class NeosUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(" user detail service for " + s);
        Account account = new Account(s);
        UserDetails user = new UserDetailsAdapter(account);
        return user;
    }

    public class UserDetailsAdapter implements UserDetails {

        Account account;

        public UserDetailsAdapter(Account account) {
            this.account = account;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
            authorities.add(new GrantedAuthorityImpl(account));
            return authorities;
        }

        @Override
        public String getPassword() {
            return account.password;
        }

        @Override
        public String getUsername() {
            return account.username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

    public class GrantedAuthorityImpl implements GrantedAuthority {
        Account account;

        GrantedAuthorityImpl(Account account) {
            this.account = account;
        }

        @Override
        public String getAuthority() {
            if (account.username.equalsIgnoreCase("root")) {
                return "ROLE_ADMIN";
            } else {
                return "ROLE_PUBLIC";
            }
        }
    }

    private class Account {
        String username;
        String password = "";

        public Account(String s) {
            username = s;
        }
    }
}