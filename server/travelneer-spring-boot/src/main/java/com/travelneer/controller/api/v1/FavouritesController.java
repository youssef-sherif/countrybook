package com.travelneer.controller.api.v1;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    public FavouritesController(FavouritePostService favouritePostService) {

        this.favouritePostService = favouritePostService;
    }


    @RequestMapping(value = "/posts/favourites/{postId}", method = RequestMethod.PUT)
    public ResponseEntity<?> favouritePost(@PathVariable("postId") int postId) {
        Map<String, Object> response = new HashMap<>();
        try {
            favouritePostService.favouritePost(postId);
            response.put("successful", "true");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            response.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/posts/favourites/{postId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> unFavouritePost(@PathVariable("postId") int postId) {
        Map<String, Object> response = new HashMap<>();
        try {
            favouritePostService.unFavouritePost(postId);
            response.put("successful", "true");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            response.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
