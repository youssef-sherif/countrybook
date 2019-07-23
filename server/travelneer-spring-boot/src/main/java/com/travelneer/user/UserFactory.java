package com.travelneer.user;

import com.travelneer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserFactory(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
       this.passwordEncoder = passwordEncoder;
    }

    public User createUser(Username username, Email email, Password password) throws Exception {
        password.encode(passwordEncoder);
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
