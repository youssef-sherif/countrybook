package com.travelneer.integration;

import com.travelneer.controller.AuthenticationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-integration-test.properties")
public class AuthenticationControllerTest {

    @Autowired
    public AuthenticationController authenticationController;

    @Test
    public void withExistingNameOrEmail_UserCanNotSignUp() {

        String username = "youssef";
        String email = "youssef@travelneer.com";
        String password = "Yo654321";

        var request = new HashMap<String, String>();
        request.put("name", username);
        request.put("email", email);
        request.put("password", password);

        ResponseEntity<Map<String, String>> responseEntity = authenticationController.signUp(request);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get("token")).isNotNull();

        username = "youssef";
        email = "youssef1@travelneer.com";
        password = "Yo654321";

        request = new HashMap<>();
        request.put("name", username);
        request.put("email", email);
        request.put("password", password);

        responseEntity = authenticationController.signUp(request);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get("token")).isNull();

        username = "youssef1";
        email = "youssef@travelneer.com";
        password = "Yo654321";

        request.put("name", username);
        request.put("email", email);
        request.put("password", password);

        responseEntity = authenticationController.signUp(request);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get("token")).isNull();
    }

    @Test
    public void withInValidNameOrPasswordOrEmail_UserCanNotSignUp() {
        String username = "sherif";
        String email = "sherif";
        String password = "Yo654321";

        var request = new HashMap<String, String>();
        request.put("name", username);
        request.put("email", email);
        request.put("password", password);

        ResponseEntity<Map<String, String>> responseEntity = authenticationController.signUp(request);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get("token")).isNull();


        username = "sh";
        email = "sherif@travelneer.com";
        password = "Yo654321";

        request = new HashMap<>();
        request.put("name", username);
        request.put("email", email);
        request.put("password", password);

        responseEntity = authenticationController.signUp(request);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get("token")).isNull();


        username = "sherif";
        email = "sherif@travelneer.com";
        password = "password";

        request = new HashMap<>();
        request.put("name", username);
        request.put("email", email);
        request.put("password", password);

        responseEntity = authenticationController.signUp(request);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get("token")).isNull();


    }

    @Test
    public void withValidNameAndPassword_UserCanSignUpAndLogIn() {
        String username = "ahmed";
        String email = "ahmed@travelneer.com";
        String password = "Yo654321";

        var request = new HashMap<String, String>();
        request.put("name", username);
        request.put("email", email);
        request.put("password", password);

        ResponseEntity<Map<String, String>> responseEntity = authenticationController.signUp(request);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get("token")).isNotNull();

        responseEntity = authenticationController.login(username, password);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get("token")).isNotNull();

    }

    @Test
    public void withInValidNameAndPassword_UserCanNotLogIn() {
        String username = "joseph";
        String password = "Yo654321";

        ResponseEntity<Map<String, String>> responseEntity = authenticationController.login(username, password);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get("token")).isNull();
    }

}
