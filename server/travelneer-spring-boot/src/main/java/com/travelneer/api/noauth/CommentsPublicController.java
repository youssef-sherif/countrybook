package com.travelneer.api.noauth;

import com.travelneer.comment.CommentFactory;
import com.travelneer.comment.CommentListResource;
import com.travelneer.comment.CommentResource;
import com.travelneer.comment.CommentTreeBuilder;
import com.travelneer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
public class CommentsPublicController {

    private final CommentFactory commentFactory;
    private final CommentTreeBuilder commentTreeBuilder;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentsPublicController(CommentFactory commentFactory, CommentTreeBuilder commentTreeBuilder, CommentRepository commentRepository) {
        this.commentFactory = commentFactory;
        this.commentTreeBuilder = commentTreeBuilder;
        this.commentRepository = commentRepository;
    }

    @RequestMapping(value = "/posts/{postId}/comments/{commentId}/tree", method = RequestMethod.GET)
    public ResponseEntity<?> getCommentTree(@PathVariable("postId") int postId,
                                            @PathVariable("commentId")int commentId,
                                            @RequestParam(name = "next", defaultValue = "0") int next) {
        try {
            CommentResource commentResource = commentFactory.getComment(postId, commentId);
            CommentListResource commentResourceList = commentTreeBuilder.buildSubTree(commentResource, next);
            commentResource.setReplies(commentResourceList);
            CommentListResource commentListResource = new CommentListResource(commentResource);

            return new ResponseEntity<>(commentListResource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/posts/{postId}/comments", method = RequestMethod.GET)
    public ResponseEntity<?> getMainComments(@PathVariable("postId")int postId,
                                             @RequestParam(name = "next", defaultValue = "0") int next)  {
        try {
            CommentListResource commentListResource =  commentTreeBuilder.buildMainTree(postId, next);

            return new ResponseEntity<>(commentListResource, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
