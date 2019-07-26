package com.travelneer.api.api.v1;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.travelneer.jooq.tables.records.FavouritesRecord;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.post.Post;
import com.travelneer.repository.PostRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.travelneer.jooq.Tables.FAVOURITES;

/**
 *
 * @author Youssef
 */
@RestController
@CrossOrigin( origins = {"http://localhost:3000", "http://localhost:5000"})
@RequestMapping(value = "/api/v1")
public class FavouritesController {

    private final JwtValidator validator;
    private final PostRepository postRepository;
    private final DSLContext create;

    @Autowired
    public FavouritesController(JwtValidator validator, PostRepository postRepository, DSLContext create) {
        this.validator = validator;
        this.postRepository = postRepository;
        this.create = create;
    }


    @RequestMapping(value = "/posts/{postId}/favourites", method = RequestMethod.PUT)
    public ResponseEntity<?> favouritePost(@PathVariable("postId") int postId) {
        Map<String, Object> response = new HashMap<>();
        try {
            FavouritesRecord record = create.newRecord(FAVOURITES);
            record.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            record.setUserId(validator.getUserId());
            record.setPostId(postId);
            record.store();

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
            FavouritesRecord record = create.newRecord(FAVOURITES);
            record.setUserId(validator.getUserId());
            record.setPostId(postId);
            record.delete();

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

    @RequestMapping(value = "/posts/{postId}/favourites-count", method = RequestMethod.GET)
    public ResponseEntity<?> getFavouritesCount(@PathVariable("postId") int postId) {
        var response = new HashMap<String, Object>();
        try {

            Integer favouritesCount = postRepository.getFavouritesCountByPostId(postId);
            response.put("favouritesCount", favouritesCount);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            response.put("successful", false);
            response.put("errorMessage", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
