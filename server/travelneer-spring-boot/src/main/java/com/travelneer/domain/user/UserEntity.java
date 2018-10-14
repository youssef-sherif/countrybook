package com.travelneer.domain.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;

public class UserEntity {

    private Integer id;
    private Username username;
    private Email email;
    private Password password;
    private Timestamp createdAt;

    public UserEntity(String username, String email, String password) {
        this.username = new Username(username);
        this.email = new Email(email);
        this.password = new Password((password), new BCryptPasswordEncoder());
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
    public Integer getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username.setValue(userName);
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void validate() throws Exception {
        if (!username.isValid()) {
            throw new Exception("Invalid Username");
        }
        else if(!email.isValid()) {
            throw new Exception("Invalid Email");
        } else if(password.getStrength() == Password.INVALID_PASSWORD) {
            throw new Exception("Invalid Password");
        }
    }

    public void validateUsername(String username) throws Exception {
        if ( !this.username.isValid(username)) {
            throw new Exception("Invalid Username");
        }
    }


    public void validateEmail(String email) throws Exception {
        if( !this.email.isValid(email) ) {
            throw new Exception("Invalid Email");
        }
    }

    public int getPasswordStrength(String value) {

        return password.getStrength(value);
    }

    public void attemptLogin(String username, String email, String password) throws Exception {

        if(!this.username.getValue().equals(username)) {
            throw new Exception("Incorrect Username");
        }

        if (!this.password.matches(password)) {
            throw new Exception("Incorrect Password");
        }
    }
}
