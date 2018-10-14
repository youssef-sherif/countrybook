/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.api;

import com.travelneer.domain.user.UserEntity;
import com.travelneer.domain.user.UserFactory;
import com.travelneer.jooq.tables.pojos.User;
import com.travelneer.jwt.JwtGenerator;
import com.travelneer.repository.UserRepository;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Youssef
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
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
    public ResponseEntity<?> signUp(@RequestBody User user) {
        var body = new HashMap<String, String>();

        try {
            UserEntity userEntity  = userFactory.createUser(user.getName(),user.getEmail(), user.getPassword());
            userEntity.validate();

            if(userRepository.exists(userEntity)) {
                throw new Exception("User already exists");
            }
            userRepository.create(userEntity);

            var token = jwtGenerator.generate(userEntity);

            body.put("token", token);

            return new ResponseEntity<>(body, HttpStatus.CREATED);
        } catch (Exception e) {
            body.put("signUpError", e.getMessage());
            System.out.println(e.getMessage());

            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/access-token",
            method = RequestMethod.GET, headers = {"Content-type=application/json"})
    public ResponseEntity<?> login(@RequestBody User user) {
    	var body = new HashMap<String, String>();

        try {
            UserEntity userEntity = userRepository.getOneByName(user.getName());
            userEntity.attemptLogin(user.getName(), user.getEmail(), user.getPassword());

            var token = jwtGenerator.generate(userEntity);

            body.put("token", token);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            body.put("loginError", e.getMessage());           
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

}
