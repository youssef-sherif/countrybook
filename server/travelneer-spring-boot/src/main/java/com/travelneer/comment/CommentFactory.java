package com.travelneer.comment;

import com.travelneer.controller.api.CommentsController;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Timestamp;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class CommentFactory {

    private final CommentRepository commentRepository;
    private final JwtValidator validator;

    @Autowired
    public CommentFactory(CommentRepository commentRepository, JwtValidator validator) {
        this.commentRepository = commentRepository;
        this.validator = validator;
    }


    public Comment createComment(int parentPostId, String content) throws Exception {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setParentPostId(parentPostId);
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        comment.setAuthorId(validator.getUserId());
        comment.setDepth((short) 0);

        comment.validate();

        commentRepository.save(comment);

        return comment;

    }

    public Comment createNestedReply(int parentPostId, int parentCommentId, String content) throws Exception {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setParentCommentId(parentCommentId);
        comment.setParentPostId(parentPostId);
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        comment.setAuthorId(validator.getUserId());
        comment.setDepth((short) (commentRepository.getDepthByCommentId(parentCommentId) + 1));

        comment.validate();

        commentRepository.save(comment);

        return comment;
    }

    public CommentResource getComment(int postId, int commentId) throws SQLException {
        Comment comment = commentRepository.getOneById(postId, commentId);
        comment.calculateTimeDifference();

        CommentResource commentResource = comment.toResource();
        commentResource.add(linkTo(methodOn(CommentsController.class).getCommentTree(postId, commentId, 0)).withSelfRel());

        return commentResource;
    }
}
