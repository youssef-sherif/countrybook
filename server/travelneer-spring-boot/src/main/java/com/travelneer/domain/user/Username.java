package com.travelneer.domain.user;

import java.util.regex.Pattern;

public class Username {

    private String value;

    private final String USER_NAME_REGEX = "((((\\w)|([0-9])|[-]|[_])){4,20}(\\b))";

    public Username(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isValid() {
        Pattern ptr = Pattern.compile(USER_NAME_REGEX);
        return ptr.matcher(value).matches();
    }

    public boolean isValid(String value) {
        Pattern ptr = Pattern.compile(USER_NAME_REGEX);
        return ptr.matcher(value).matches();
    }
}
