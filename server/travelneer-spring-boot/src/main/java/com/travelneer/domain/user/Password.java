package com.travelneer.domain.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

public class Password {

    private String encodedPassword;

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

    public Password() {}

    public Password(String encodedPassword) {
        this.encodedPassword = encodedPassword;
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

    public String getEncoded() {
        return encodedPassword;
    }

    public void setEncoded(String password) {
        this.encodedPassword = password;
    }
}
