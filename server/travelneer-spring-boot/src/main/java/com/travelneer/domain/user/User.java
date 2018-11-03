package com.travelneer.domain.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

public class UserEntity {

    private Integer id;
    private Username name;
    private Email email;
    private Password password;
    private Timestamp createdAt;
    private PasswordEncoder passwordEncoder;


    public UserEntity() {
        this.name = new Username();
        this.email = new Email();
        this.password = new Password();
        this.passwordEncoder = new BCryptPasswordEncoder();

    }

    public UserEntity(String username, String email, String password) {
        this.name = new Username(username);
        this.email = new Email(email);
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.password = new Password(this.passwordEncoder.encode(password));
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "UserEntity{" +
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

    public void login(String password) throws Exception {
        if(!passwordEncoder.matches(password, this.password.getEncoded())) {
            throw new Exception("Invalid Username or Password");
        }
    }

    public void validate() throws Exception {
        this.validateEmail(this.email.getValue());
        this.validateUsername(this.name.getValue());
    }
}
