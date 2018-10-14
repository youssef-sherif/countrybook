package com.travelneer.domain.user;

import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public UserEntity createUser(String username, String email, String password) {
        return new UserEntity(username, email, password);
    }

}
