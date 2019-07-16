package com.travelneer.post;

import com.travelneer.controller.api.CommentsController;
import com.travelneer.jwt.JwtValidator;
import com.travelneer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

        comment.validate();

        commentRepository.save(comment);

        return comment;

    }

    public Comment createNestedReply(int commentId, String content) throws Exception {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setParentCommentId(commentId);
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        comment.setAuthorId(validator.getUserId());

        comment.validate();

        commentRepository.save(comment);

        return comment;
    }

    public CommentListResource getComments(int postId, int page) throws SQLException {

        List<Comment> comments = commentRepository.getCommentsByParentPostId(postId, page);

        comments.forEach(Comment::calculateTimeDifference);

        List<CommentResource> commentResources = comments.stream().map(Comment::toResource)
                .collect(Collectors.toList());

        for (CommentResource commentResource : commentResources) {
            CommentListResource replies = getNestedReplies(commentResource.getCommentId(), 0);
            commentResource.setReplies(replies);
        }

        CommentListResource commentListResource = new CommentListResource(commentResources);
        commentListResource.add(linkTo(methodOn(CommentsController.class).getComments(postId, page)).withSelfRel());
        commentListResource.add(linkTo(methodOn(CommentsController.class).getComments(postId, page+10)).withRel("next"));

        return commentListResource;
    }

    public CommentListResource getNestedReplies(int commentId, int page) throws SQLException {

        List<Comment> comments = commentRepository.getCommentsByParentCommentId(commentId, page);

        comments.forEach(Comment::calculateTimeDifference);
        List<CommentResource> commentResources = comments.stream().map(Comment::toResource)
                .collect(Collectors.toList());

        for(CommentResource commentResource: commentResources) {
            CommentListResource replies = getNestedReplies(commentResource.getCommentId(), 0);
            commentResource.setReplies(replies);
        }

        CommentListResource commentListResource = new CommentListResource(commentResources);
        commentListResource.add(linkTo(methodOn(CommentsController.class).getNestedReplies(commentId, page)).withSelfRel());
        commentListResource.add(linkTo(methodOn(CommentsController.class).getNestedReplies(commentId, page+10)).withRel("next"));

        return commentListResource;
    }

    public CommentResource getComment(int commentId) {
        return null;
    }
}
