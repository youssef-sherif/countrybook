package com.travelneer.unit.user;

import com.travelneer.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-unit-test.properties")
public class UserTest {

    @Test
    public void whenSettingPassword_ItShouldEncrypt() {
        User user = new User();
        String password = "12345678";
        user.setPassword(password);
        assertThat(user.getPassword()).isNotEqualTo(password);
    }
}
