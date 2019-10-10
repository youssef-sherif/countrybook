package com.travelneer.user;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void onCreation_PasswordShouldEncrypt_And_PasswordShouldMatchEncrypted() {
        String passwordValue = "12345678";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Password password = new Password(passwordValue);
        password.encode(passwordEncoder);


        User user = new User(new Username("youssef"),
                new Email("youssef@travelneer.com"),
                password);

        assertThat(user.getPassword().getEncoded()).isNotEqualTo(passwordValue);
        assertThat(passwordEncoder.matches(passwordValue, password.getEncoded())).isEqualTo(true);

    }
}
