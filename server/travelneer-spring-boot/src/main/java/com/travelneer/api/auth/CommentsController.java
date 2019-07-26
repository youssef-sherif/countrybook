package com.travelneer.api.api;

import com.travelneer.comment.CommentFactory;
import com.travelneer.comment.CommentTreeBuilder;
import com.travelneer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin( origins = {"http://localhost:3000", "http://localhost:5000"})
@RequestMapping(value = "/api")
public class CommentsController {

    private final CommentFactory commentFactory;
    private final CommentTreeBuilder commentTreeBuilder;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentsController(CommentFactory commentFactory, CommentTreeBuilder commentTreeBuilder, CommentRepository commentRepository) {
        this.commentFactory = commentFactory;
        this.commentTreeBuilder = commentTreeBuilder;
        this.commentRepository = commentRepository;
    }

    @RequestMapping(value = "/posts/{postId}/comments/{commentId}/replies", method = RequestMethod.POST)
    public ResponseEntity<?> replyToComment(@PathVariable("postId") int postId,
                                            @PathVariable("commentId")int commentId,
                                            @RequestBody Map<String, String> request)  {
        var response = new HashMap<>();
        try {
            commentFactory.createNestedReply(postId, commentId, request.get("content"));

            response.put("created", true);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/posts/{postId}/comments", method = RequestMethod.POST)
    public ResponseEntity<?> newComment(@PathVariable("postId")int postId,
                                        @RequestBody Map<String, String> request)  {
        var response = new HashMap<>();
        try {
            commentFactory.createComment(postId, request.get("content"));
            response.put("created", true);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            response.put("error", e.getMessage());
            response.put("created", false);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}