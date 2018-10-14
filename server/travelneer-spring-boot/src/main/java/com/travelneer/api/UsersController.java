/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Youssef
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/api")
public class UsersController {
   

    @RequestMapping(value = "/user",
            method = {RequestMethod.GET}, headers = {"Content-type=application/json"})
    public ResponseEntity<?> user() {
    	
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
