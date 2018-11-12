package com.travelneer.controller.api.v1;

import com.travelneer.post.FeedResource;
import com.travelneer.post.Post;
import com.travelneer.post.PostResource;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
@CrossOrigin(origins = { "http://localhost:3000" })
@RequestMapping(value = "/api/v1")
public class CountryPostsController {

    private final PostRepository postRepository;

    @Autowired
    public CountryPostsController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @RequestMapping(value = "/countries/{countryId}/posts", method = RequestMethod.GET)
    public ResponseEntity<?> getCountryPosts(@PathVariable("countryId") short countryId) {

        try {
            List<Post> posts = postRepository.getPostsByCountryId(countryId);
            posts.forEach(Post::calculateTimeDifference);
            List<PostResource> postResources = posts.stream().map(Post::toResource)
                    .collect(Collectors.toList());

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
            Integer postsCount = postRepository.getPostsCountByCountryId(countryId);

            responseBody.put("postsCount", postsCount);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            responseBody.put("successful", false);
            responseBody.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }
}
