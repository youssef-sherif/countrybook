package com.travelneer.api.auth;

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
@RequestMapping(value = "/auth")
public class CommentsController {

    private final CommentFactory commentFactory;

    @Autowired
    public CommentsController(CommentFactory commentFactory) {
        this.commentFactory = commentFactory;
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
