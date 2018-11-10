package com.travelneer.controller.api.v1;

import com.travelneer.hateoas.FeedResource;
import com.travelneer.hateoas.PostResource;

import java.util.HashMap;
import java.util.List;

import com.travelneer.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Youssef
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000" })
@RequestMapping(value = "/api/v1")
public class CountryPostsController {

    private final PostService postService;

    @Autowired
    public CountryPostsController(PostService postService) {
        this.postService = postService;
    }


    @RequestMapping(value = "/countries/{countryId}/posts", method = RequestMethod.GET)
    public ResponseEntity<?> getCountryPosts(@PathVariable("countryId") short countryId) {

        try {
            List<PostResource> postResources = postService.getCountryPosts(countryId);
            var feedResource = new FeedResource(postResources);

            return new ResponseEntity<>(feedResource, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/countries/{countryId}/posts-count", method = RequestMethod.GET)
    public ResponseEntity<?> getPostsCount(@PathVariable("countryId") Short countryId) {
        var responseBody = new HashMap<String, Object>();
        try {
            Integer postsCount = postService.getCountryPostsCount(countryId);

            responseBody.put("postsCount", postsCount);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }
}
