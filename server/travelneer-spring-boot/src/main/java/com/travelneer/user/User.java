package com.travelneer.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

public class User {

    private Integer id;
    private Username name;
    private Email email;
    private Password password;
    private Timestamp createdAt;


    public User() {
        this.name = new Username();
        this.email = new Email();
        this.password = new Password();
    }

    public User(Username username, Email email, Password password) {
        this.name = username;
        this.email = email;
        this.password = password;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + name.getValue() +
                ", password=" + password.getEncoded() +
                ", email=" + email.getValue() +
                ", createdAt=" + createdAt +
                '}';
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public void setPassword(String password) {
        this.password.setEncoded(password);
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    public Integer getId() {
        return id;
    }


    public Username getName() {
        return name;
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


    public void login(String password) throws Exception {
        if(!this.password.getPasswordEncoder().matches(password, this.password.getEncoded())) {
            throw new Exception("Invalid Username or Password");
        }
    }

    public void validateUsername(String username) throws Exception {
        if ( !this.name.isValid(username)) {
            throw new Exception("Invalid Username");
        }
    }


    public void validateEmail(String email) throws Exception {
        if( !this.email.isValid(email) ) {
            throw new Exception("Invalid Email");
        }
    }

    public void validate() throws Exception {

        if(!this.name.isValid()) {
            throw new Exception("Invalid Username");
        }

        if(!this.email.isValid()) {
            throw new Exception("Invalid Email");
        }

        if(password.getStrength() == Password.INVALID_PASSWORD) {
            throw new Exception("Weak Password");
        }
    }
}
