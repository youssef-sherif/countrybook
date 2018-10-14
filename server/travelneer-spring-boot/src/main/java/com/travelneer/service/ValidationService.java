/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.service;

import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.stereotype.Service;

/**
 *
 * @author Youssef
 */
@Service
public class ValidationService {

    public static final int STRONG_PASSWORD = 1;
    public static final int MEDIUM_PASSWORD = 2;
    public static final int INVALID_PASSWORD = 3;

    private final String USER_NAME_REGEX = "((((\\w)|([0-9])|[-]|[_])){4,20}(\\b))";

    private final String STRONG_PASSWORD_REGEX = "^"
            + "(?=.*\\d)"
            + "(?=.*[a-z])"
            + "(?=.*[A-Z])"
            + "(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])"
            + "."
            + "{8,15}"
            + "$";

    private final String MEDIUM_PASSWORD_REGEX = "^"
            + "(?=.*\\d)"
            + "(?=.*[a-z])"                        
            + "."
            + "{6,15}"
            + "$";

    public boolean isValidEmail(String email) {
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (AddressException e) {
            return false;
        }
        return true;
    }

    public boolean isValidUserName(String userName) {
        Pattern ptr = Pattern.compile(USER_NAME_REGEX);
        return ptr.matcher(userName).matches();
    }

    public int getPasswordStrength(String password) {

        Pattern strPtr = Pattern.compile(STRONG_PASSWORD_REGEX);
        Pattern mdmPtr = Pattern.compile(MEDIUM_PASSWORD_REGEX);
        if (strPtr.matcher(password).matches()) {
            return STRONG_PASSWORD;
        } else if (mdmPtr.matcher(password).matches()) {
            return MEDIUM_PASSWORD;
        } else {
            return INVALID_PASSWORD;
        }
    }

}
