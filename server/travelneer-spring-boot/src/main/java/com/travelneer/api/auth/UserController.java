package com.travelneer.api.auth;

import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CountryRepository;
import com.travelneer.repository.PostRepository;
import com.travelneer.repository.UserRepository;
import com.travelneer.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RestController
public class UserController {

    private final JwtValidator validator;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public UserController(JwtValidator validator, UserRepository userRepository, PostRepository postRepository, CountryRepository countryRepository) {
        this.validator = validator;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.countryRepository = countryRepository;
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

}
