package com.travelneer.user;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Email {
    private String email;

    public Email() {}

    public Email(String value) {
        this.email = value;
    }

    public String getValue() {
        return email;
    }

    public void setValue(String value) {
        this.email = value;
    }

    public boolean isValid() {
        try {
            InternetAddress internetAddress = new InternetAddress(email);
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
