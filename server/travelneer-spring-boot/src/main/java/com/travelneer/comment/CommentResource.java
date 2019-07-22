/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.comment;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.hateoas.ResourceSupport;

import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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
    private Integer parentCommentId;
    private Short depth;

    private CommentListResource replies;


    @JsonCreator
    public CommentResource(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.name = comment.getName();
        this.email = comment.getEmail();
        this.timeDiff = comment.getTimeDiff();
        this.parentPostId = comment.getParentPostId();
        this.parentCommentId = comment.getParentCommentId();
        this.depth = comment.getDepth();
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

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public Short getDepth() {
        return depth;
    }

    public CommentListResource getReplies() {
        return replies;
    }

    public void setReplies(CommentListResource replies) {
        this.replies = replies;
    }
}
