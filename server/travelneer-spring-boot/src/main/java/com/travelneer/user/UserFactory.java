package com.travelneer.user;

import com.travelneer.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private final UserRepository userRepository;

    public UserFactory(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User createUser(Username username, Email email, Password password) throws Exception {
        User user = new User(username, email, password);
        user.validate();

        if(userRepository.exists(user)) {
            throw new Exception("User already exists");
        }
        userRepository.save(user);

        return user;
    }

    public User createUser() {
        return new User();
    }
}
