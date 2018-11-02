/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.jwt;

import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Youssef
 */
public class JwtUserDetails implements UserDetails {

    private final String username;
    private final Date expiration;
    private final Integer userId;

    public JwtUserDetails(String username, Date expiration, Integer userId) {
        this.username = username;
        this.expiration = expiration;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        Date now = new Date(System.currentTimeMillis());
        if (now.before(expiration)) {
            return true;
        }
        return false;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
