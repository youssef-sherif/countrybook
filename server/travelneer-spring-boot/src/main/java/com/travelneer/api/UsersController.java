/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.api;

import com.travelneer.domain.user.UserEntity;
import com.travelneer.jwt.JwtUserDetails;
import com.travelneer.jwt.JwtValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Youssef
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/api")
public class UsersController {

    private final JwtValidator validator;

    public UsersController(JwtValidator validator) {
        this.validator = validator;
    }

    @RequestMapping(value = "/me",
            method = {RequestMethod.GET}, headers = {"Content-type=application/json"})
    public ResponseEntity<?> getUserDetails() {

        JwtUserDetails user = validator.getUserDetails();
    	
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
