/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.service;

import com.travelneer.jwt.JwtUserDetails;
import com.travelneer.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Youssef
 */
@Service
public class JwtValidatorService {

    private JwtUserDetails userDetails;
    private Integer userId;
    
    public void validate(String token) throws AuthenticationException {
        
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token)
                    .getBody();            
            
            this.userDetails = new JwtUserDetails(body.getSubject(), body.getExpiration());
            this.userId = Integer.parseInt(body.getId());
        } catch (MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new AuthenticationException("Invalid Token"){};
        }
    }

    public JwtUserDetails getUserDetails() {
        return userDetails;
    }

    public int getUserId() {
        return userId;
    }
}
