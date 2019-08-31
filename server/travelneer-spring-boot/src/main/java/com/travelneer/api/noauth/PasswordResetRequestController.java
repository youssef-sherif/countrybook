package com.travelneer.api.noauth;

import com.travelneer.jwt.JwtGenerator;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.UserRepository;
import com.travelneer.user.Password;
import com.travelneer.user.User;
import com.travelneer.user.UserMailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin( origins = {"http://localhost:3000", "http://localhost:5000"})
public class PasswordsController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final JwtValidator jwtValidator;
    private final UserMailer userMailer;

    @Autowired
    public PasswordsController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator, JwtValidator jwtValidator, UserMailer userMailer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.jwtValidator = jwtValidator;
        this.userMailer = userMailer;
    }

    @RequestMapping(value = "/password-reset-token",
            method = RequestMethod.GET,  params = "email")
    public ResponseEntity<Map<String, String>> getPasswordResetToken(@RequestParam(name = "email") String email) {

        var response = new HashMap<String, String>();
        try {
            User user = userRepository.getOneByEmail(email);
            String token = jwtGenerator.generatePasswordResetToken(user);

//            userMailer.sendPasswordResetEmail(user.getEmail(), token);

            response.put("token", token);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
