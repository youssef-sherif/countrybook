package com.travelneer.comment;

import com.travelneer.controller.api.CommentsController;
import com.travelneer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CommentTreeBuilder {

    private final CommentRepository commentRepository;
    public static int MAX_DEPTH = 5;

    @Autowired
    public CommentTreeBuilder(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentListResource buildMainTree(int postId, int page) throws SQLException {

        List<Comment> comments = commentRepository.getCommentsByParentPostId(postId, page);

        comments.forEach(Comment::calculateTimeDifference);

        List<CommentResource> commentResourceList = comments.stream().map(Comment::toResource)
                .collect(Collectors.toList());

        for (CommentResource e : commentResourceList) {
            List<CommentResource> replies = getNestedReplies(e);
            e.setReplies(new CommentListResource(replies));
        }

        CommentListResource commentListResource = new CommentListResource(commentResourceList);
        commentListResource.add(linkTo(methodOn(CommentsController.class).getMainComments(postId, page)).withSelfRel());
        commentListResource.add(linkTo(methodOn(CommentsController.class).getMainComments(postId, page+10)).withRel("next"));

        return commentListResource;
    }

    public CommentListResource buildSubTree(CommentResource commentResource, int page) throws SQLException {

        List<Comment> comments = commentRepository.getCommentsByParentCommentId(commentResource.getParentPostId(), commentResource.getCommentId(), page);

        comments.forEach(Comment::calculateTimeDifference);

        List<CommentResource> commentResourceList = comments.stream().map(Comment::toResource)
                .collect(Collectors.toList());

        for(CommentResource e : commentResourceList) {
            List<CommentResource> replies = getNestedReplies(e);
            e.setReplies(new CommentListResource(replies));
        }

        return new CommentListResource(commentResourceList);
    }

    private List<CommentResource> getNestedReplies(CommentResource commentResource) throws SQLException {
        List<Comment> comments = commentRepository.getCommentsByParentCommentId(
                commentResource.getParentPostId(), commentResource.getCommentId(), 0);

        comments.forEach(Comment::calculateTimeDifference);
        List<CommentResource> commentResourceList = comments.stream().map(Comment::toResource)
                .collect(Collectors.toList());

        if(commentResource.getDepth() == MAX_DEPTH) return null;

        for(CommentResource e : commentResourceList) {
            List<CommentResource> replies = getNestedReplies(e);
            e.setReplies(new CommentListResource(replies));
        }

        commentResource.add(linkTo(methodOn(CommentsController.class).getCommentTree(commentResource.getParentPostId(), commentResource.getCommentId(), 0)).withSelfRel());

        return commentResourceList;
    }

}