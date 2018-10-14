package com.travelneer.api.v1;

import com.travelneer.hateoas.PostResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            List<PostResource> posts = postService.getCountryPosts(countryId);

            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
