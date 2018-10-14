package com.travelneer.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

public class Password {

    private String value;
    private String encrypted;
    private final PasswordEncoder passwordEncoder;

    public static final int STRONG_PASSWORD = 1;
    public static final int MEDIUM_PASSWORD = 2;
    public static final int INVALID_PASSWORD = 3;

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

    public Password(String value, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.value = value;
        this.encrypted = passwordEncoder.encode(value);
    }

    public int getStrength(String value) {

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
        if (strPtr.matcher(value).matches()) {
            return STRONG_PASSWORD;
        } else if (mdmPtr.matcher(value).matches()) {
            return MEDIUM_PASSWORD;
        } else {
            return INVALID_PASSWORD;
        }
    }

    public boolean matches(String value) {

        return passwordEncoder.matches(encrypted, value);
    }

    public String getValue() {
        return this.encrypted;
    }
}
