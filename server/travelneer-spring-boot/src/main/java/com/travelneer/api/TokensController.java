/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.api;

import com.travelneer.domain.user.UserEntity;
import com.travelneer.domain.user.UserFactory;
import com.travelneer.dto.UserSignUpDto;
import com.travelneer.jwt.JwtGenerator;
import com.travelneer.repository.UserRepository;
import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    private final ModelMapper modelMapper;

    @Autowired
    public TokensController(JwtGenerator jwtGenerator, UserRepository userRepository, UserFactory userFactory, ModelMapper modelMapper) {
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<?> signUp(@RequestBody UserSignUpDto user) {
        var body = new HashMap<String, String>();

        try {
            UserEntity userEntity  = userFactory.createUser(user.getName(), user.getEmail(), user.getPassword());

            if(userRepository.exists(userEntity)) {
                throw new Exception("User already exists");
            }
            userRepository.save(userEntity);

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
    public ResponseEntity<?> login(HttpServletRequest request) {
    	var body = new HashMap<String, String>();

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserEntity userEntity = userRepository.getOneByName(username);
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
