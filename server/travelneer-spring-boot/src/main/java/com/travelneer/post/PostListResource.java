
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.post;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travelneer.controller.api.PostsController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class PostListResource extends ResourceSupport {

    private  final List<PostResource> postResources;

    public PostListResource(List<PostResource> postResources) {
        this.postResources = postResources;
    }

    @JsonProperty("posts")
    public List<PostResource> getPosts() {
        return postResources;
    }

}