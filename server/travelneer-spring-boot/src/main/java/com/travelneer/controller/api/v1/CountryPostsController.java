package com.travelneer.controller.api.v1;

import com.travelneer.post.PostFactory;

import java.util.HashMap;

import com.travelneer.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Youssef
 */
@RestController
@CrossOrigin( origins = {"http://localhost:3000", "http://localhost:5000"})
@RequestMapping(value = "/api/v1")
public class CountryPostsController {

    private final PostRepository postRepository;
    private final PostFactory postFactory;

    @Autowired
    public CountryPostsController(PostRepository postRepository, PostFactory postFactory) {
        this.postRepository = postRepository;
        this.postFactory = postFactory;
    }


    @RequestMapping(value = "/countries/{countryCode}/posts", method = RequestMethod.GET)
    public ResponseEntity<?> getCountryPosts(@PathVariable("countryCode") String countryCode,
                                             @RequestParam(name = "next", defaultValue = "0") int next) {

        try {
            var feedResource = postFactory.getCountryPosts(countryCode, next);

            return new ResponseEntity<>(feedResource, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries/{countryCode}/posts-count", method = RequestMethod.GET)
    public ResponseEntity<?> getPostsCount(@PathVariable("countryCode") String countryCode) {
        var responseBody = new HashMap<String, Object>();
        try {
            Integer postsCount = postRepository.getPostsCountByCountryCode(countryCode);

            responseBody.put("postsCount", postsCount);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }
}
