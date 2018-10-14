package com.travelneer.domain.user;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Email {
    private String value;

    public Email(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isValid() {
        try {
            InternetAddress internetAddress = new InternetAddress(value);
            internetAddress.validate();
        } catch (AddressException e) {
            return false;
        }
        return true;
    }

    public boolean isValid(String value) {
        try {
            InternetAddress internetAddress = new InternetAddress(value);
            internetAddress.validate();
        } catch (AddressException e) {
            return false;
        }
        return true;
    }
}
