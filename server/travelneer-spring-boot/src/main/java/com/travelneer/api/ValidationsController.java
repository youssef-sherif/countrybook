/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.api;

import com.travelneer.domain.user.UserEntity;
import com.travelneer.domain.user.UserFactory;
import com.travelneer.service.ValidationService;

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
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class ValidationsController {

    private final UserEntity userEntity;

    @Autowired
    public ValidationsController(UserFactory userFactory) {
        this.userEntity = userFactory.createUser("", "", "");
    }

    @RequestMapping(value = "/validations", method = RequestMethod.GET, params = "userName")
    public ResponseEntity<?> validateUsername(HttpServletRequest request) {

        var body = new HashMap<String, Object>();
        String userName = request.getParameter("userName");

        try {
            userEntity.validateUsername(userName);

            body.put("isValid", true);
            body.put("userName", userName);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch(Exception e) {
            body.put("isValid", false);
            body.put("userName", userName);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/validations", method = RequestMethod.GET, params = "email")
    public ResponseEntity<?> validateEmail(HttpServletRequest request) {

        var body = new HashMap<String, Object>();
        String email = request.getParameter("email");

        try {
            userEntity.validateEmail(email);

            body.put("isValid", true);
            body.put("email", email);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch(Exception e) {
            body.put("isValid", false);
            body.put("email", email);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/validations", method = RequestMethod.GET, params = "password")
    public ResponseEntity<?> validatePassword(HttpServletRequest request) {

        var body = new HashMap<String, Object>();
        String password = request.getParameter("password");

        switch (userEntity.getPasswordStrength(password)) {
            case ValidationService.STRONG_PASSWORD:
                body.put("passwordStrength", ValidationService.STRONG_PASSWORD);
                break;
            case ValidationService.MEDIUM_PASSWORD:
                body.put("passwordStrength", ValidationService.MEDIUM_PASSWORD);
                break;
            default:
                body.put("passwordStrength", ValidationService.INVALID_PASSWORD);
                break;
        }
        body.put("password", password);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
