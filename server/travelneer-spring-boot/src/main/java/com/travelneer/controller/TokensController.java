/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.controller;

import com.travelneer.user.*;
import com.travelneer.dto.UserSignUpDto;
import com.travelneer.jwt.JwtGenerator;
import com.travelneer.repository.UserRepository;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Youssef
 */
@CrossOrigin( "http://localhost:3000")
@RestController
public class TokensController {

    private final JwtGenerator jwtGenerator;
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @Autowired
    public TokensController(JwtGenerator jwtGenerator, UserRepository userRepository, UserFactory userFactory) {
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<?> signUp(@RequestBody UserSignUpDto signUpDto) {
        var body = new HashMap<String, String>();

        try {
            User user  = userFactory.createUser(new Username(signUpDto.getName()),
                            new Email(signUpDto.getEmail()),
                            new Password(signUpDto.getPassword()));
            if(userRepository.exists(user)) {
                throw new Exception("User already exists");
            }
            userRepository.save(user);

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
    public ResponseEntity<?> login(HttpServletRequest request) {
    	var body = new HashMap<String, String>();

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

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

}
