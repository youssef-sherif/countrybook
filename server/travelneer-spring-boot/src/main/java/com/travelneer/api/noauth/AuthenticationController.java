/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.api.noauth;

import com.travelneer.repository.UserRepository;
import com.travelneer.user.*;
import com.travelneer.jwt.JwtGenerator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Youssef
 */
@RestController
@CrossOrigin( origins = {"http://localhost:3000", "http://localhost:5000"})
public class AuthenticationController {

    private final JwtGenerator jwtGenerator;
    private final UserFactory userFactory;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMailer userMailer;


    @Autowired
    public AuthenticationController(JwtGenerator jwtGenerator, UserFactory userFactory, PasswordEncoder passwordEncoder, UserRepository userRepository, UserMailer userMailer) {
        this.jwtGenerator = jwtGenerator;
        this.userFactory = userFactory;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMailer = userMailer;
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<Map<String, String>> signUp(@RequestBody Map<String, String> request) {
        var body = new HashMap<String, String>();

        try {
            User user  = userFactory.createUser(new Username(request.get("name")),
                            new Email(request.get("email")),
                            new Password(request.get("password")));

            var token = jwtGenerator.generateAccessToken(user);

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
            user.login(password, passwordEncoder);

            var token = jwtGenerator.generateAccessToken(user);

            body.put("token", token);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            body.put("loginError", e.getMessage());
            
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/password-reset-token",
            method = RequestMethod.GET,  params = "email")
    public ResponseEntity<Map<String, String>> getPasswordResetToken(@RequestParam(name = "email") String email) {

        var response = new HashMap<String, String>();
        try {
            User user = userRepository.getOneByEmail(email);
            String token = jwtGenerator.generatePasswordResetToken(user);

            userMailer.sendPasswordResetEmail(user.getEmail(), token);

            response.put("token", token);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
