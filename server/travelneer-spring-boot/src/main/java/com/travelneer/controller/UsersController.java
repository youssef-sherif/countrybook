/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.controller;

import com.travelneer.user.User;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 *
 * @author Youssef
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/controller")
public class UsersController {

    private final JwtValidator validator;
    private final UserRepository repository;

    @Autowired
    public UsersController(JwtValidator validator, UserRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    @RequestMapping(value = "/me",
            method = RequestMethod.GET, headers = {"Content-type=application/json"})
    public ResponseEntity<?> getUserDetails() {

        try {
            var body = new HashMap<String, String>();
            User userEntity = repository.getOneById(validator.getUserId());
            body.put("name", userEntity.getName().getValue());

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
