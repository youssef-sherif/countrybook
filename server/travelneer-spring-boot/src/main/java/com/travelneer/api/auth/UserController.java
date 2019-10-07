package com.travelneer.api.auth;

import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CountryRepository;
import com.travelneer.repository.PostRepository;
import com.travelneer.repository.UserRepository;
import com.travelneer.user.Password;
import com.travelneer.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RestController
public class UserController {

    private final JwtValidator validator;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CountryRepository countryRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(JwtValidator validator, UserRepository userRepository, PostRepository postRepository, CountryRepository countryRepository, PasswordEncoder passwordEncoder) {
        this.validator = validator;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.countryRepository = countryRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping(value = "/auth/me", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo() {

        try {
            User user = userRepository.getOneById(validator.getUserId());
            var userResource = user.toResource();

            return new ResponseEntity<>(userResource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/auth/me/posts-count", method = RequestMethod.GET)
    public ResponseEntity<?> getUserPostsCount() {

        var responseBody = new HashMap<String, Object>();
        try {
            Integer postsCount = postRepository.getPostsCountByUserId(validator.getUserId());

            responseBody.put("postsCount", postsCount);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

        @RequestMapping(value = "/auth/me/countries-followed-count", method = RequestMethod.GET)
    public ResponseEntity<?> getCountriesFollowedCount() {

        var responseBody = new HashMap<String, Object>();
        try {
            Integer countriesFollowedCount = countryRepository.getCountriesFollowedCount(validator.getUserId());

            responseBody.put("countriesFollowedCount", countriesFollowedCount);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/auth/me/password",
            method = RequestMethod.PUT, headers = {"Content-type=application/json"})
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody Map<String, String> request) {

        var response = new HashMap<String, String>();
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");
        try {
            String currentEncodedPassword = userRepository.getOneById(validator.getUserId()).getPassword().getEncoded();
            if(!passwordEncoder.matches(oldPassword, currentEncodedPassword)) {
                throw new Exception("Incorrect Password");
            }

            String password = passwordEncoder.encode(newPassword);

            if(Password.getStrength(newPassword) == Password.INVALID_PASSWORD) {
                throw new Exception("Invalid Password");
            }

            userRepository.updatePasswordById(validator.getUserId(), password);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("error", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
