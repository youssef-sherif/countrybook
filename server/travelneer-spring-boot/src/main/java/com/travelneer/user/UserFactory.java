package com.travelneer.user;

import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUser(Username username, Email email, Password password) throws Exception {
        User user = new User(username, email, password);
        user.validate();
        return user;
    }

    public User createUser() {
        return new User();
    }
}
