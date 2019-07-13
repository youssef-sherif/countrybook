package com.travelneer.controller.api.v1;

import com.travelneer.post.PostFactory;
import com.travelneer.post.PostListResource;
import com.travelneer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin( origins = {"http://localhost:3000", "http://localhost:5000"})
@RequestMapping(value = "/api/v1")
public class CommentsController {

    private final PostFactory postFactory;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentsController(PostFactory postFactory, CommentRepository commentRepository) {
        this.postFactory = postFactory;
        this.commentRepository = commentRepository;
    }

    @RequestMapping(value = "/posts/{postId}/comments", method = RequestMethod.GET)
    public ResponseEntity<?> getComments(@PathVariable("postId")int postId,
                                         @RequestParam(name = "next", defaultValue = "0") int next)  {
        try {
            PostListResource postListResource =  postFactory.getComments(postId, next);

            return new ResponseEntity<>(postListResource, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/posts/{postId}/comments", method = RequestMethod.POST)
    public ResponseEntity<?> newComment(@PathVariable("postId")int postId,
                                        @RequestBody Map<String, String> request)  {
        var response = new HashMap<>();
        try {
            postFactory.createComment(postId, request.get("comment"));
            response.put("created", true);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            response.put("error", e.getMessage());
            response.put("created", false);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/posts/{postId}/comments-count", method = RequestMethod.GET)
    public ResponseEntity<?> getCommentsCount(@PathVariable("postId")int postId)  {
        var response = new HashMap<String, Object>();
        try {
            Integer commentsCount = commentRepository.getCommentsCountByParentPostId(postId);
            response.put("commentsCount", commentsCount);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            response.put("errorMessage", e.getMessage());

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
