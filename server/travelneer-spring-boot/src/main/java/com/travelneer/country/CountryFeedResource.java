package com.travelneer.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travelneer.controller.api.v1.CountryPostsController;
import com.travelneer.post.PostResource;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CountryFeedResource extends ResourceSupport {

    private  final List<PostResource> postResources;
    public CountryFeedResource(List<PostResource> postResources, short countryId, int currentPage) {
        this.postResources = postResources;

        this.add(linkTo(methodOn(CountryPostsController.class).getCountryPosts(countryId, currentPage)).withSelfRel());
    }

    @JsonProperty("posts")
    public List<PostResource> getPosts() {
        return postResources;
    }

}
