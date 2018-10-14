/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.service;

import com.travelneer.jooq.tables.pojos.User;
import com.travelneer.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.springframework.stereotype.Service;

/**
 *
 * @author Youssef
 */
@Service
public class JwtGeneratorService {

    public String generate(User user) {
        Claims claims = Jwts.claims()
                .setId(Integer.toString(user.getId()))
                .setSubject(user.getName())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

}
