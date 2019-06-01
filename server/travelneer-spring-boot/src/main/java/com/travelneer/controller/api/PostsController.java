package com.travelneer.controller.api;

import com.travelneer.dto.NewPostDTO;
import com.travelneer.post.FeedResource;
import com.travelneer.post.PostFactory;

import java.util.HashMap;

import com.travelneer.post.PostResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author Youssef
 */
@CrossOrigin( origins = {"http://localhost:3000", "http://localhost:5000"})
@RequestMapping(value = "/api")
@RestController
public class PostsController {

	private final PostFactory postFactory;

	@Autowired
	public PostsController(PostFactory postFactory) {
		this.postFactory = postFactory;
	}

	@RequestMapping(value = "/feed", method = RequestMethod.GET)
	public ResponseEntity<?> getFeed(@RequestParam(name = "next", defaultValue = "0") int next) {
		try {

			FeedResource feedResource = postFactory.getFeed(next);
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

			postFactory.createPost(postDTO.getContent(), postDTO.getCountryId());

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
