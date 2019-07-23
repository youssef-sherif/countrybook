/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.jwt;

import com.travelneer.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 * @author Youssef
 */
@Service
public class JwtGenerator {

    @Value("${key.password-reset}")
    private String passwordResetSecret;

    @Value("${key.access}")
    private String accessSecret;

    @Value("${token-expiration-time.password-reset}")
    private Long passwordResetTokenExpirationTime;

    @Value("${token-expiration-time.access}")
    private Long accessTokenExpirationTime;

    public String generatePasswordResetToken(User user) {
        Claims claims = Jwts.claims()
                .setId(Integer.toString(user.getId()))
                .setSubject(user.getName().getValue())
                .setExpiration(new Date(System.currentTimeMillis() + passwordResetTokenExpirationTime));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, passwordResetSecret)
                .compact();
    }

    public String generateAccessToken(User user) {
        Claims claims = Jwts.claims()
                .setId(Integer.toString(user.getId()))
                .setSubject(user.getName().getValue())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpirationTime));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, accessSecret)
                .compact();
    }

}
