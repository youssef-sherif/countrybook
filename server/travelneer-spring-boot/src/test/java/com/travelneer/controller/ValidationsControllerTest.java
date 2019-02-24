package com.travelneer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationsControllerTest {

    @Autowired
    ValidationsController validationsController;

    @Test
    public void withStrongPassword() {
        String password = "Yo^%4321";

        assertThat(validationsController.validatePassword(password).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(validationsController.validatePassword(password).getBody()).get("passwordStrength")).isEqualTo(1);
    }

    @Test
    public void withMediumPassword() {
        String password = "Yo654321";

        assertThat(validationsController.validatePassword(password).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(validationsController.validatePassword(password).getBody()).get("passwordStrength"))
                .isEqualTo(2);
    }

    @Test
    public void withInvalidPassword() {
        String password = "password";

        assertThat(validationsController.validatePassword(password).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(validationsController.validatePassword(password).getBody()).get("passwordStrength"))
                .isEqualTo(3);
    }

}
