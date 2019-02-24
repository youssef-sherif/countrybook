/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.controller;

import com.travelneer.user.Password;
import com.travelneer.user.User;
import com.travelneer.user.UserFactory;

import java.util.HashMap;
import java.util.Map;

import com.travelneer.repository.UserRepository;
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
public class ValidationsController {

    private final User user;
    private final UserRepository userRepository;

    @Autowired
    public ValidationsController(UserFactory userFactory, UserRepository userRepository) {
        this.user = userFactory.createUser();
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/validations", method = RequestMethod.GET, params = "username")
    public ResponseEntity<Map<String, Object>> validateUsername(@RequestParam(name = "username") String username) {

        var body = new HashMap<String, Object>();

        try {
            if(userRepository.nameExists(username)) {
                throw new Exception("Username Exists");
            }
            user.validateUsername(username);

            body.put("isValid", true);
            body.put("username", username);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch(Exception e) {
            body.put("isValid", false);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/validations", method = RequestMethod.GET, params = "email")
    public ResponseEntity<Map<String, Object>> validateEmail(@RequestParam(name = "email") String email) {

        var body = new HashMap<String, Object>();

        try {
            if(userRepository.emailExists(email)) {
                throw new Exception("Email exists");
            }
            user.validateEmail(email);

            body.put("isValid", true);
            body.put("email", email);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch(Exception e) {
            body.put("isValid", false);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/validations", method = RequestMethod.GET, params = "password")
    public ResponseEntity<Map<String, Object>> validatePassword(@RequestParam(name = "password") String password) {

        var body = new HashMap<String, Object>();

        switch (Password.getStrength(password)) {
            case Password.STRONG_PASSWORD:
                body.put("passwordStrength", Password.STRONG_PASSWORD);
                break;
            case Password.MEDIUM_PASSWORD:
                body.put("passwordStrength", Password.MEDIUM_PASSWORD);
                break;
            default:
                body.put("passwordStrength", Password.INVALID_PASSWORD);
                break;
        }
        body.put("password", password);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
