package com.travelneer.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

public class Password {

    private String encodedPassword;
    private String passsword;
    private PasswordEncoder passwordEncoder;


    public static final int STRONG_PASSWORD = 1;
    public static final int MEDIUM_PASSWORD = 2;
    public static final int INVALID_PASSWORD = 3;

    private static final String STRONG_PASSWORD_REGEX = "^"
            + "(?=.*\\d)"
            + "(?=.*[a-z])"
            + "(?=.*[A-Z])"
            + "(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])"
            + "."
            + "{8,15}"
            + "$";

    private static final String MEDIUM_PASSWORD_REGEX = "^"
            + "(?=.*\\d)"
            + "(?=.*[a-z])"
            + "."
            + "{6,15}"
            + "$";

    public Password(String password) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.encodedPassword = this.passwordEncoder.encode(password);
        this.passsword = password;
    }

    public Password() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public static int getStrength(String value) {

        Pattern strPtr = Pattern.compile(STRONG_PASSWORD_REGEX);
        Pattern mdmPtr = Pattern.compile(MEDIUM_PASSWORD_REGEX);
        if (strPtr.matcher(value).matches()) {
            return STRONG_PASSWORD;
        } else if (mdmPtr.matcher(value).matches()) {
            return MEDIUM_PASSWORD;
        } else {
            return INVALID_PASSWORD;
        }
    }

    public int getStrength() {
        Pattern strPtr = Pattern.compile(STRONG_PASSWORD_REGEX);
        Pattern mdmPtr = Pattern.compile(MEDIUM_PASSWORD_REGEX);
        if (strPtr.matcher(passsword).matches()) {
            return STRONG_PASSWORD;
        } else if (mdmPtr.matcher(passsword).matches()) {
            return MEDIUM_PASSWORD;
        } else {
            return INVALID_PASSWORD;
        }
    }

    public String getEncoded() {
        return encodedPassword;
    }

    public void setEncoded(String password) {
        this.encodedPassword = password;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
