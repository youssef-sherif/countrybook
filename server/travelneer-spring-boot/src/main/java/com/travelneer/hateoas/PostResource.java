/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelneer.hateoas;

import com.travelneer.api.PostsController;
import com.travelneer.api.v1.FavouritesController;
import com.travelneer.jooq.tables.pojos.Post;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author Youssef
 */
public class PostResource extends ResourceSupport {

    private final Post post;
    private boolean isFavourite;

    public PostResource(Post post) {
        this.post = post;

        this.add(linkTo(methodOn(PostsController.class).getPost(post.getId())).withSelfRel());
        this.add(linkTo(methodOn(FavouritesController.class).favouritePost(post.getId())).withRel("favourite"));
        this.add(linkTo(methodOn(FavouritesController.class).unFavouritePost(post.getId())).withRel("unFavourite"));
    }

    public Post getPost() {
        return post;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
