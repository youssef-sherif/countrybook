/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.controller;

import com.travelneer.user.User;
import com.travelneer.user.UserFactory;
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
    public ResponseEntity<?> signUp(@RequestBody UserSignUpDto user) {
        var body = new HashMap<String, String>();

        try {
            User userEntity  = userFactory.createUser(user.getName(), user.getEmail(), user.getPassword());
            userEntity.validate();
            if(userRepository.exists(userEntity)) {
                throw new Exception("User already exists");
            }
            userRepository.save(userEntity);

            var token = jwtGenerator.generate(userEntity);

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

            User userEntity = userRepository.getOneByName(username);
            userEntity.login(password);

            var token = jwtGenerator.generate(userEntity);

            body.put("token", token);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            body.put("loginError", e.getMessage());
            
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

}
