/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Youssef
 */
@Service
public class JwtValidator {

    private JwtUserDetails userDetails;
    private Integer userId;

    @Value("${jwt.key}")
    private String jwtKey;

    public void validateToken(String token) throws AuthenticationException {

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(jwtKey)
                    .parseClaimsJws(token)
                    .getBody();

            this.userDetails = new JwtUserDetails(body.getSubject(), body.getExpiration(), Integer.parseInt(body.getId()));
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
