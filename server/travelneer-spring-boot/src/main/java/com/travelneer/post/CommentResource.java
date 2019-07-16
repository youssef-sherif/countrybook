/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.travelneer.controller.api.CommentsController;
import com.travelneer.controller.api.PostsController;
import com.travelneer.controller.api.v1.FavouritesController;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class CommentResource extends ResourceSupport {


    private final Integer commentId;
    private final String content;
    private final String name;
    private final String email;
    private final Map<Long, String> timeDiff;

    private Boolean isFavourite;
    private Integer parentPostId;

    private CommentListResource replies;


    @JsonCreator
    public CommentResource(Comment comment, int next) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.name = comment.getName();
        this.email = comment.getEmail();
        this.timeDiff = comment.getTimeDiff();
        this.parentPostId = comment.getParentPostId();

        add(linkTo(methodOn(CommentsController.class).getComments(commentId, 0)).withSelfRel());
        add(linkTo(methodOn(CommentsController.class).getNestedReplies(commentId, next)).withRel("replies"));
    }

    public Integer getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTimeDiff() {
        Long time = (Long) timeDiff.keySet().toArray()[0];

        return  time + " " + timeDiff.get(time);
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }


    public Integer getParentPostId() {
        return parentPostId;
    }

    public CommentListResource getReplies() {
        return replies;
    }

    public void setReplies(CommentListResource replies) {
        this.replies = replies;
    }
}
