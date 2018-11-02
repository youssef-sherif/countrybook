
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.hateoas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.travelneer.api.CountriesController;
import com.travelneer.api.PostsController;
import com.travelneer.api.v1.SearchCountriesController;
import com.travelneer.api.v1.CountryFollowsController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class FeedResource extends ResourceSupport {

    private  final List<PostResource> postResources;

    public FeedResource(@JsonProperty("posts") List<PostResource> postResources) {
        this.postResources = postResources;

        this.add(linkTo(methodOn(PostsController.class).getFeed()).withSelfRel());
    }

    public List<PostResource> getPosts() {
        return postResources;
    }

}