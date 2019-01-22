package com.travelneer.controller.api;

import com.travelneer.dto.NewPostDTO;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.post.FeedResource;
import com.travelneer.post.PostFactory;

import java.util.HashMap;
import java.util.List;

import com.travelneer.post.Post;
import com.travelneer.post.PostResource;
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

	private final PostFactory postFactory;

	@Autowired
	public PostsController(PostFactory postFactory) {
		this.postFactory = postFactory;
	}

	@RequestMapping(value = "/feed", method = RequestMethod.GET)
	public ResponseEntity<?> getFeed() {

		try {
			FeedResource feedResource = postFactory.getFeed();
			return new ResponseEntity<>(feedResource, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/feed",
			method = RequestMethod.POST, headers = {"Content-type=application/json"})
	public ResponseEntity<?> newPost(@RequestBody NewPostDTO postDTO) {

		var body = new HashMap<>();
		try {

			Post post = postFactory.createPost(postDTO.getContent(), postDTO.getCountryId());

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

			PostResource postResource = postFactory.getPost(postId);

			return new ResponseEntity<>(postResource, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
