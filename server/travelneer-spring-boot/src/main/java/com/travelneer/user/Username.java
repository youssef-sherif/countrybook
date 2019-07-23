package com.travelneer.user;

import java.util.regex.Pattern;

public class Username {

    private String name;

    private static final String USER_NAME_REGEX = "((((\\w)|([0-9])|[-]|[_])){4,20}(\\b))";

    public Username() {}

    public Username(String value) {
        this.name = value;
    }

    public String getValue() {
        return name;
    }

    public void setValue(String value) {
        this.name = value;
    }

    public boolean isValid() {
        Pattern ptr = Pattern.compile(USER_NAME_REGEX);
        return ptr.matcher(name).matches();
    }

}
