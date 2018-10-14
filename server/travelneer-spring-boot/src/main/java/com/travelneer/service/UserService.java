/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.service;

import com.travelneer.jooq.tables.pojos.User;
import com.travelneer.repository.UserRepository;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Youssef
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtGeneratorService jwtGenerator;

    @Autowired
    public UserService(JwtGeneratorService jwtGenerator, UserRepository userDAO, ValidationService validationService, PasswordEncoder passwordEncoder) {
        this.userRepository = userDAO;
        this.validationService = validationService;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }


    
//    public String create(User user) throws Exception {
//
//        validate(user);
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//        userRepository.create(user);
//
//        return jwtGenerator.generate(user);
//
//    }


    private void validate(User user) throws Exception {

        if (!validationService.isValidUserName(user.getName()) ||
                userRepository.nameExists(user.getName())) {
            throw new Exception("Invalid Username");
        }
        else if(!validationService.isValidEmail(user.getEmail()) ||
                userRepository.emailExists(user.getEmail())) {
            throw new Exception("Invalid Email");
        }
    }

    public boolean validateUserName(String userName) throws Exception {
        if (!validationService.isValidUserName(userName) || userRepository.nameExists(userName)) {
            throw new Exception("Invalid Username");
        }
        return true;
    }

    public boolean validateEmail(String email) throws Exception {
        if(!validationService.isValidEmail(email) || userRepository.emailExists(email)) {
            throw new Exception("Invalid Email");
        }
        return true;
    }

    
//    public String login(User user) throws Exception {
//        User fetchedUser = userRepository.getOneByName(user.getName());
//
//        if (passwordEncoder.matches(user.getPassword(), fetchedUser.getPassword())) {
//            return jwtGenerator.generate(fetchedUser);
//        } else {
//            throw new Exception("Incorrect Password");
//        }
//    }

}
