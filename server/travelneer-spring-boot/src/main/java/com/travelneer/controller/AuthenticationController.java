/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.integration.controller;

import com.travelneer.jwt.JwtValidator;
import com.travelneer.user.*;
import com.travelneer.dto.UserSignUpDTO;
import com.travelneer.jwt.JwtGenerator;
import com.travelneer.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Youssef
 */
@CrossOrigin( "http://localhost:3000")
@RestController
public class AuthenticationController {

    private final JwtGenerator jwtGenerator;
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final JwtValidator validator;

    @Autowired
    public AuthenticationController(JwtGenerator jwtGenerator, UserRepository userRepository, UserFactory userFactory, JwtValidator validator) {
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.validator = validator;
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<Map<String, String>> signUp(@RequestBody UserSignUpDTO signUpDto) {
        var body = new HashMap<String, String>();

        try {
            User user  = userFactory.createUser(new Username(signUpDto.getName()),
                            new Email(signUpDto.getEmail()),
                            new Password(signUpDto.getPassword()));

            var token = jwtGenerator.generate(user);

            body.put("token", token);

            return new ResponseEntity<>(body, HttpStatus.CREATED);
        } catch (Exception e) {
            body.put("signUpError", e.getMessage());

            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/access-token",
            method = RequestMethod.GET, headers = {"Content-type=application/json"})
    public ResponseEntity<Map<String, String>> login(@RequestParam(name = "username") String username,
                                     @RequestParam(name = "password") String password) {
    	var body = new HashMap<String, String>();

        try {

            User user = userRepository.getOneByName(username);
            user.login(password);

            var token = jwtGenerator.generate(user);

            body.put("token", token);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            body.put("loginError", e.getMessage());
            
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/me", method = RequestMethod.GET)
    public ResponseEntity<?> getUserDetails() {

        try {
            var body = new HashMap<String, String>();
            User userEntity = userRepository.getOneById(validator.getUserId());
            body.put("name", userEntity.getName().getValue());
            body.put("userId", Integer.toString(validator.getUserId()));

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
