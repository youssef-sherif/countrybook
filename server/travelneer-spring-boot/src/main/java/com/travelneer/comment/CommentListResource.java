
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.post;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author Youssef
 */
public class CommentListResource extends ResourceSupport {

    private  final List<CommentResource> commentResources;

    public CommentListResource(List<CommentResource> commentResources) {
        this.commentResources = commentResources;
    }

    public CommentListResource(CommentResource commentResource) {
        commentResources = new ArrayList<>();
        commentResources.add(commentResource);
    }

    @JsonProperty("comments")
    public List<CommentResource> getComments() {
        return commentResources;
    }

}