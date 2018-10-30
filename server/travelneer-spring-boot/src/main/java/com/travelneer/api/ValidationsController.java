/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.api;

import com.travelneer.domain.user.Password;
import com.travelneer.domain.user.UserEntity;
import com.travelneer.domain.user.UserFactory;

import java.util.HashMap;

import com.travelneer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Youssef
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class ValidationsController {

    private final UserEntity userEntity;
    private final UserRepository userRepository;

    @Autowired
    public ValidationsController(UserFactory userFactory, UserRepository userRepository) {
        this.userEntity = userFactory.createUser("", "", "");
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/validations", method = RequestMethod.GET, params = "username")
    public ResponseEntity<?> validateUsername(HttpServletRequest request) {

        var body = new HashMap<String, Object>();

        try {
            String username = request.getParameter("username");
            if(userRepository.nameExists(username)) {
                throw new Exception("Username Exists");
            }
            userEntity.validateUsername(username);

            body.put("isValid", true);
            body.put("username", username);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch(Exception e) {
            body.put("isValid", false);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/validations", method = RequestMethod.GET, params = "email")
    public ResponseEntity<?> validateEmail(HttpServletRequest request) {

        var body = new HashMap<String, Object>();

        try {
            String email = request.getParameter("email");
            if(userRepository.emailExists(email)) {
                throw new Exception("Email exists");
            }
            userEntity.validateEmail(email);

            body.put("isValid", true);
            body.put("email", email);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch(Exception e) {
            body.put("isValid", false);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/validations", method = RequestMethod.GET, params = "password")
    public ResponseEntity<?> validatePassword(HttpServletRequest request) {

        var body = new HashMap<String, Object>();
        String password = request.getParameter("password");

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
