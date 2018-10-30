/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.jwt;

import com.travelneer.domain.user.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 * @author Youssef
 */
@Service
public class JwtGenerator {

    public String generate(UserEntity userEntity) {
        Claims claims = Jwts.claims()
                .setId(Integer.toString(userEntity.getId()))
                .setSubject(userEntity.getName().getValue())
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstants.EXPIRATION_TIME));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, JwtConstants.SECRET)
                .compact();
    }

}
