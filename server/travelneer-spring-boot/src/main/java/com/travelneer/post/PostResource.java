/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.travelneer.controller.api.PostsController;
import com.travelneer.controller.api.v1.FavouritesController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class PostResource extends ResourceSupport {

    private final Post post;
    private Boolean isFavourite;

    @JsonCreator
    public PostResource(Post post) {
        this.post = post;

        this.add(linkTo(methodOn(PostsController.class).getPost(post.getId())).withSelfRel());
        this.add(linkTo(methodOn(FavouritesController.class).favouritePost(post.getId())).withRel("favourite"));
        this.add(linkTo(methodOn(FavouritesController.class).unFavouritePost(post.getId())).withRel("unFavourite"));
    }

    @JsonProperty("post")
    public Post getPost() {
        return post;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

}
