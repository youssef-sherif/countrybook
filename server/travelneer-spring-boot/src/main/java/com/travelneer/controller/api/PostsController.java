package com.travelneer.controller.api;

import com.travelneer.dto.Favourites;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.post.FeedResource;
import com.travelneer.post.PostResource;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.travelneer.post.Post;
import com.travelneer.repository.FavouritesRepository;
import com.travelneer.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Youssef
 */
@CrossOrigin("http://localhost:3000")
@RequestMapping(value = "/api")
@RestController
public class PostsController {

	private final PostRepository postRepository;
	private final FavouritesRepository favouritesRepository;
	private final JwtValidator validator;

	@Autowired
	public PostsController(PostRepository postRepository, FavouritesRepository favouritesRepository, JwtValidator validator) {
        this.postRepository = postRepository;
		this.favouritesRepository = favouritesRepository;
		this.validator = validator;
    }

	@RequestMapping(value = "/feed", method = RequestMethod.GET)
	public ResponseEntity<?> getFeed() {

		try {
            List<Post> posts = postRepository.getFeed(validator.getUserId());
            posts.forEach(Post::calculateTimeDifference);
            List<PostResource> postResources = posts.stream().map(Post::toResource)
                    .collect(Collectors.toList());
            postResources.forEach(e ->
					e.setFavourite(favouritesRepository.isPostFavouriteByUser(e.getPostId(), validator.getUserId())));
			var feedResource = new FeedResource(postResources);

			return new ResponseEntity<>(feedResource, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/feed",
			method = RequestMethod.POST, headers = {"Content-type=application/json"})
	public ResponseEntity<?> newPost(@RequestBody Post post) {

		var body = new HashMap<>();
		try {
			if(post.getContent().isEmpty()) {
				throw new Exception("Empty post");
			}

			if(post.getCountryId() == null) {
				throw new Exception("No country selected");
			}
			
            post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            post.setAuthorId(validator.getUserId());

            postRepository.save(post);

            body.put("created", true);

            return new ResponseEntity<>(body, HttpStatus.OK);
		} catch(Exception e) {
			body.put("error", e.getMessage());
			body.put("created", false);
			return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
	public ResponseEntity<?> getPost(@PathVariable("postId") int postId) {

		try {
            Post post =  postRepository.getOneById(postId);
            post.calculateTimeDifference();
            var postResource = post.toResource();
            postResource.setFavourite(favouritesRepository.isPostFavouriteByUser(postId, validator.getUserId()));

			return new ResponseEntity<>(postResource, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
