package com.travelneer.domain.user;

import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUser(String username, String email, String password) {
        return new User(username, email, password);
    }

}
