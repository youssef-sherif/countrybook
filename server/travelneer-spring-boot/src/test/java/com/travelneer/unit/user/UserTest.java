package com.travelneer.unit.user;

import com.travelneer.user.Email;
import com.travelneer.user.Password;
import com.travelneer.user.User;
import com.travelneer.user.Username;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-unit-test.properties")
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

        assertThat(user.getPassword()).isNotEqualTo(passwordValue);
        assertThat(passwordEncoder.matches(passwordValue, password.getEncoded())).isEqualTo(true);

    }
}
