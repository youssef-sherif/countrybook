
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.post;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;


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