package com.travelneer.integration.controller.api.v1;

import java.util.HashMap;
import java.util.Map;

import com.travelneer.post.Post;
import com.travelneer.repository.PostRepository;
import com.travelneer.service.FavouritePostService;
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
public class FavouritesController {

    private final FavouritePostService favouritePostService;
    private final PostRepository postRepository;

    @Autowired
    public FavouritesController(FavouritePostService favouritePostService, PostRepository postRepository) {
        this.favouritePostService = favouritePostService;
        this.postRepository = postRepository;
    }


    @RequestMapping(value = "/posts/{postId}/favourites", method = RequestMethod.PUT)
    public ResponseEntity<?> favouritePost(@PathVariable("postId") int postId) {
        Map<String, Object> response = new HashMap<>();
        try {
            favouritePostService.favouritePost(postId);
            Post post =  postRepository.getOneById(postId);
            post.calculateTimeDifference();
            var postResource = post.toResource();
            postResource.setFavourite(true);
            response.put("post", postResource);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            response.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/posts/{postId}/favourites", method = RequestMethod.DELETE)
    public ResponseEntity<?> unFavouritePost(@PathVariable("postId") int postId) {
        Map<String, Object> response = new HashMap<>();
        try {
            favouritePostService.unFavouritePost(postId);
            Post post =  postRepository.getOneById(postId);
            post.calculateTimeDifference();
            var postResource = post.toResource();
            postResource.setFavourite(false);
            response.put("post", postResource);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            response.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
